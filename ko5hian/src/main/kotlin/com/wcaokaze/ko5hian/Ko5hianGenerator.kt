package com.wcaokaze.ko5hian

import java.io.*

internal class Ko5hianGenerator(private val outDir: File,
                                private val conf: ParsedConfiguration)
{
    companion object {
        val KO5HIAN_VERSION = "0.1.0"
        val FILE_HEADER = "// Ko5hian Version: $KO5HIAN_VERSION"
    }

    private val runtimeFile = File(outDir, "ko5hian/runtime.kt")

    private fun shouldWriteRuntime(): Boolean {
        if (!runtimeFile.exists()) return true

        return try {
            runtimeFile.useLines { it.first() != FILE_HEADER }
        } catch (e: IOException) {
            true
        }
    }

    fun writeRuntime() {
        if (!shouldWriteRuntime()) return

        runtimeFile.parentFile.mkdirs()

        runtimeFile.writeText("""
            $FILE_HEADER
            package ko5hian

            import android.content.Context
            import android.view.View
            import android.view.ViewGroup

            import kotlin.contracts.*

            @DslMarker
            @Retention(AnnotationRetention.SOURCE)
            @Target(AnnotationTarget.TYPE)
            annotation class Ko5hianViewDslMarker

            inline class Ko5hianRoot(val context: Context)

            class Ko5hianViewHolder<V : View, L : ViewGroup.LayoutParams>(
                val context: Context,
                val view: V,
                val layout: L
            )

            @ExperimentalContracts
            inline fun <V : View> ko5hian(context: Context, builder: Ko5hianRoot.() -> V): V {
                contract {
                    callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
                }

                return Ko5hianRoot(context).builder()
            }

            @ExperimentalContracts
            inline fun <V : View> ko5hian(view: V, builder: Ko5hianViewHolder<V, *>.() -> V): V {
                contract {
                    callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
                }

                val vh = Ko5hianViewHolder(view.context, view, view.layoutParams)
                vh.builder()
                return view
            }

            const val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT
            const val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT
        """.trimIndent())
    }

    fun writeKo5hian() {
        val packageDir = File(outDir, conf.outPackage.replace(".", File.separator))

        packageDir.mkdirs()

        for (view in conf.views) {
            val file = File(packageDir, "${view.className}.kt")
            val functionName = getFunctionNameFor(view.className)

            file.writer().use {
                it.write("""
                    $FILE_HEADER
                    package ${conf.outPackage}
                """.trimIndent())

                if (conf.outPackage != "ko5hian") {
                    it.write("\nimport ko5hian\n")
                }

                it.write("""
                    import kotlin.contracts.*

                    @ExperimentalContracts
                    inline fun Ko5hianRoot.`$functionName`(
                            builder: Ko5hianViewHolder<${view.fullyClassName},
                                    android.view.ViewGroup.LayoutParams>.() -> Unit
                    ): ${view.fullyClassName} {
                        contract {
                            callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
                        }

                        val v = ${view.instantiatorExpression}
                        val l = android.view.ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                        v.layoutParams = l
                        val vh = Ko5hianViewHolder(context, v, l)
                        vh.builder()
                        return v
                    }
                """.trimIndent())

                for (viewGroup in conf.viewGroups) {
                    it.write("""
                        @ExperimentalContracts
                        @JvmName("${functionName}For${viewGroup.className}")
                        inline fun Ko5hianViewHolder<${viewGroup.fullyClassName}, *>.`$functionName`(
                                builder: Ko5hianViewHolder<${view.fullyClassName},
                                        ${viewGroup.lParamsClassName}>.() -> Unit
                        ): ${view.fullyClassName} {
                            contract {
                                callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
                            }

                            val v = ${view.instantiatorExpression}
                            val l = ${viewGroup.lParamsInstantiatorExpression}
                            v.layoutParams = l
                            val vh = Ko5hianViewHolder(context, v, l)
                            vh.builder()
                            return v
                        }
                    """.trimIndent())
                }
            }
        }
    }

    /**
     * HOGEView -> hogeView
     */
    private fun getFunctionNameFor(className: String): String {
        val i = className.indexOfFirst { !it.isUpperCase() } - 1

        if (i < 1) return className

        return className.substring(0, i).toLowerCase() + className.substring(i)
    }
}
