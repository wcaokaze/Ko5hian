package com.wcaokaze.ko5hian

import java.io.*

class RuntimeFileGenerator(outDir: File) {
   private val packageDir = File(outDir, "ko5hian")

   init {
      if (!packageDir.exists()) {
         packageDir.mkdirs()
      }
   }

   private val ko5hianRootFile       = File(packageDir, "Ko5hianRoot.kt")
   private val ko5hianMarkerFile     = File(packageDir, "Ko5hianMarker.kt")
   private val ko5hianViewHolderFile = File(packageDir, "Ko5hianViewHolder.kt")
   private val ko5hianFile           = File(packageDir, "ko5hian.kt")
   private val layoutParamsFile      = File(packageDir, "layoutParams.kt")
   private val gravitiesFile         = File(packageDir, "gravities.kt")
   private val imageViewsFile        = File(packageDir, "imageViews.kt")

   fun generate() {
      if (!shouldGenerate()) return

      generateKo5hianRoot()
      generateKo5hianMarker()
      generateKo5hianViewHolder()
      generateKo5hian()
      generateLayoutParams()
      generateGravities()
      generateImageViews()
   }

   private fun shouldGenerate() = !ko5hianRootFile.exists()

   private fun generateKo5hianRoot() {
      ko5hianRootFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         package ko5hian

         import android.content.Context

         inline class Ko5hianRoot(val context: Context)
      """.trimIndent())
   }

   private fun generateKo5hianMarker() {
      ko5hianMarkerFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         package ko5hian

         @DslMarker
         @Retention(AnnotationRetention.SOURCE)
         @Target(AnnotationTarget.CLASS)
         annotation class Ko5hianMarker
      """.trimIndent())
   }

   private fun generateKo5hianViewHolder() {
      ko5hianViewHolderFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         package ko5hian

         import android.content.Context
         import android.view.View
         import android.view.ViewGroup

         @Ko5hianMarker
         class Ko5hianViewHolder<V : View, L : ViewGroup.LayoutParams>(
            val context: Context,
            val view: V,
            val layout: L,
            val displayDensity: Float
         ) {
            constructor(context: Context, view: V, layout: L) : this(
                  context, view, layout,
                  view.context.resources.displayMetrics.density
            )

            fun <CV : View, CL : ViewGroup.LayoutParams>
                  createChild(view: CV, layout: CL): Ko5hianViewHolder<CV, CL>
            {
               return Ko5hianViewHolder(
                     context, view, layout,
                     displayDensity
               )
            }

            fun dip(dip: Int): Int {
               val px = (dip * displayDensity).toInt()

               return when {
                  px != 0 -> px
                  dip < 0 -> -1
                  else    ->  1
               }
            }
         }
      """.trimIndent())
   }

   private fun generateKo5hian() {
      ko5hianFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         package ko5hian

         import android.content.Context
         import android.view.View
         import android.view.ViewGroup

         import kotlin.contracts.*

         @ExperimentalContracts
         inline fun <V : View> ko5hian(context: Context, builder: Ko5hianRoot.() -> V): V {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            return Ko5hianRoot(context).builder()
         }

         @ExperimentalContracts @JvmName("ko5hianWithoutLParamsType")
         inline fun <V : View> ko5hian(view: V, builder: Ko5hianViewHolder<V, *>.() -> Unit): V {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            val vh = Ko5hianViewHolder(view.context, view, view.layoutParams)

            vh.builder()

            view.layoutParams = vh.layout

            return view
         }

         @ExperimentalContracts
         inline fun <V : View, L : ViewGroup.LayoutParams>
               ko5hian(view: View, builder: Ko5hianViewHolder<V, L>.() -> Unit): V
         {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            @Suppress("UNCHECKED_CAST")
            val vh = Ko5hianViewHolder(view.context, view as V, view.layoutParams as L)

            vh.builder()

            view.layoutParams = vh.layout

            return view
         }
      """.trimIndent())
   }

   private fun generateLayoutParams() {
      layoutParamsFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         package ko5hian

         import android.view.ViewGroup

         val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT
         val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT
      """.trimIndent())
   }

   private fun generateGravities() {
      gravitiesFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         package ko5hian

         import android.view.Gravity

         val Ko5hianViewHolder<*, *>.START  get() = Gravity.START
         val Ko5hianViewHolder<*, *>.TOP    get() = Gravity.TOP
         val Ko5hianViewHolder<*, *>.END    get() = Gravity.END
         val Ko5hianViewHolder<*, *>.BOTTOM get() = Gravity.BOTTOM
         val Ko5hianViewHolder<*, *>.CENTER            get() = Gravity.CENTER
         val Ko5hianViewHolder<*, *>.CENTER_HORIZONTAL get() = Gravity.CENTER_HORIZONTAL
         val Ko5hianViewHolder<*, *>.CENTER_VERTICAL   get() = Gravity.CENTER_VERTICAL
      """.trimIndent())
   }

   private fun generateImageViews() {
      imageViewsFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         // This is a sample. You can add extensions like the follow.

         package ko5hian

         import android.widget.ImageView

         val Ko5hianViewHolder<ImageView, *>.CENTER        get() = ImageView.ScaleType.CENTER
         val Ko5hianViewHolder<ImageView, *>.CENTER_CROP   get() = ImageView.ScaleType.CENTER_CROP
         val Ko5hianViewHolder<ImageView, *>.CENTER_INSIDE get() = ImageView.ScaleType.CENTER_INSIDE
         val Ko5hianViewHolder<ImageView, *>.FIX_CENTER    get() = ImageView.ScaleType.FIX_CENTER
         val Ko5hianViewHolder<ImageView, *>.FIX_END       get() = ImageView.ScaleType.FIX_END
         val Ko5hianViewHolder<ImageView, *>.FIX_START     get() = ImageView.ScaleType.FIX_START
         val Ko5hianViewHolder<ImageView, *>.FIX_XY        get() = ImageView.ScaleType.FIX_XY
         val Ko5hianViewHolder<ImageView, *>.MATRIX        get() = ImageView.ScaleType.MATRIX
      """.trimIndent())
   }
}
