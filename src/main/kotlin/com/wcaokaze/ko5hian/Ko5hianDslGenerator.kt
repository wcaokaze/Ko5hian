package com.wcaokaze.ko5hian

import java.io.*

internal class Ko5hianDslGenerator(private val outDir: File,
                                   private val conf: Ko5hianConfiguration)
{
   companion object {
      val KO5HIAN_VERSION = "2.0.1"
      val FILE_HEADER = "// Ko5hian Version: $KO5HIAN_VERSION"
   }

   fun writeKo5hian() {
      val packageDir = File(outDir, conf.outPackage.replace(".", File.separator))

      packageDir.mkdirs()

      for (view in conf.views) {
         val functionName = getFunctionNameFor(view.className)
         val file = File(packageDir, "$functionName.kt")

         file.writer().use {
            it.write("""
               $FILE_HEADER
               package ${conf.outPackage}

            """.trimIndent())

            if (conf.outPackage != "ko5hian") {
               it.write("""

                  import ko5hian.Ko5hian
                  import ko5hian.Ko5hianRoot
                  import ko5hian.MATCH_PARENT
                  import ko5hian.WRAP_CONTENT

               """.trimIndent())
            }

            it.write("""

               import kotlin.contracts.*

               @ExperimentalContracts
               inline fun Ko5hianRoot.`$functionName`(
                     builder: Ko5hian<${view.fullyClassName},
                           android.view.ViewGroup.LayoutParams>.() -> Unit
               ): ${view.fullyClassName} {
                  contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

                  val v = ${view.instantiatorExpression}
                  val l = android.view.ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

                  v.layoutParams = l

                  val vh = Ko5hian(context, v, l)

                  vh.builder()
                  return v
               }

            """.trimIndent())

            for (viewGroup in conf.viewGroups) {
               it.write("""

                  @ExperimentalContracts
                  @JvmName("${functionName}For${viewGroup.className}")
                  inline fun Ko5hian<${viewGroup.fullyClassName}, *>.`$functionName`(
                        builder: Ko5hian<${view.fullyClassName},
                              ${viewGroup.lParamsFullyClassName}>.() -> Unit
                  ): ${view.fullyClassName} {
                     contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

                     val v = ${view.instantiatorExpression}
                     val l = ${viewGroup.lParamsInstantiatorExpression}
                     v.layoutParams = l
                     view.addView(v)
                     val vh = createChild(v, l)
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
        val i = className.indexOfFirst { !it.isUpperCase() }

        return when {
            i  < 1 -> className
            i == 1 -> className.first()            .toLowerCase() + className.drop(1)
            else   -> className.substring(0, i - 1).toLowerCase() + className.substring(i - 1)
        }
    }
}
