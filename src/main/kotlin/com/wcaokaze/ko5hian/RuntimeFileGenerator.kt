package com.wcaokaze.ko5hian

import java.io.*

class RuntimeFileGenerator(outDir: File) {
   private val packageDir = File(outDir, "ko5hian")

   init {
      if (!packageDir.exists()) {
         packageDir.mkdirs()
      }
   }

   private val ko5hianRootFile   = File(packageDir, "Ko5hianRoot.kt")
   private val ko5hianMarkerFile = File(packageDir, "Ko5hianMarker.kt")
   private val ko5hianFile       = File(packageDir, "Ko5hian.kt")
   private val layoutParamsFile  = File(packageDir, "layoutParams.kt")
   private val gravitiesFile     = File(packageDir, "gravities.kt")
   private val imageViewsFile    = File(packageDir, "imageViews.kt")

   fun generate() {
      if (!shouldGenerate()) return

      generateKo5hianRoot()
      generateKo5hianMarker()
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

   private fun generateKo5hian() {
      ko5hianFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         package ko5hian

         import android.content.Context
         import android.view.View
         import android.view.ViewGroup

         import kotlin.contracts.*

         @Ko5hianMarker
         class Ko5hian<V : View, L : ViewGroup.LayoutParams?>(
            val context: Context,
            val view: V,
            val layout: L,
            private val displayDensity: Float
         ) {
            constructor(context: Context, view: V, layout: L) : this(
                  context, view, layout,
                  context.resources.displayMetrics.density
            )

            fun <CV : View, CL : ViewGroup.LayoutParams>
                  createChild(view: CV, layout: CL): Ko5hian<CV, CL>
            {
               return Ko5hian(
                     context, view, layout,
                     displayDensity
               )
            }

            val Int.dip: Int get() {
               val px = (this * displayDensity).toInt()

               return when {
                  px  != 0 -> px
                  this < 0 -> -1
                  else     ->  1
               }
            }
         }

         @ExperimentalContracts
         inline fun <V> ko5hian(context: Context, builder: Ko5hianRoot.() -> V): V {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            return Ko5hianRoot(context).builder()
         }

         @ExperimentalContracts
         inline fun <V : ViewGroup, C : View>
               V.addView(builder: Ko5hian<V, *>.() -> C): C
         {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            val vh = Ko5hian(context, this, layoutParams)

            return vh.builder()
         }

         @ExperimentalContracts @JvmName("ko5hianWithoutLParamsType")
         inline fun <V : View> ko5hian(view: V, builder: Ko5hian<V, *>.() -> Unit) {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            val vh = Ko5hian(view.context, view, view.layoutParams)

            vh.builder()
         }

         @ExperimentalContracts
         inline fun <reified V : View, reified L : ViewGroup.LayoutParams>
               ko5hian(view: View, builder: Ko5hian<V, L>.() -> Unit)
         {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            val vh = Ko5hian(view.context, view as V, view.layoutParams as L)

            vh.builder()
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
         @file:Suppress("UNUSED")
         package ko5hian

         import android.view.Gravity

         val Ko5hian<*, *>.START  get() = Gravity.START
         val Ko5hian<*, *>.TOP    get() = Gravity.TOP
         val Ko5hian<*, *>.END    get() = Gravity.END
         val Ko5hian<*, *>.BOTTOM get() = Gravity.BOTTOM
         val Ko5hian<*, *>.CENTER            get() = Gravity.CENTER
         val Ko5hian<*, *>.CENTER_HORIZONTAL get() = Gravity.CENTER_HORIZONTAL
         val Ko5hian<*, *>.CENTER_VERTICAL   get() = Gravity.CENTER_VERTICAL
      """.trimIndent())
   }

   private fun generateImageViews() {
      imageViewsFile.writeText("""
         ${Ko5hianGenerator.FILE_HEADER}
         // This is a sample. You can add extensions like the follow.

         @file:Suppress("UNUSED")
         package ko5hian

         import android.view.Gravity
         import android.widget.ImageView
         import android.graphics.drawable.Drawable

         val Ko5hian<ImageView, *>.SCALE_TYPE_CENTER get() = ImageView.ScaleType.CENTER
         val Ko5hian<ImageView, *>.CENTER_CROP       get() = ImageView.ScaleType.CENTER_CROP
         val Ko5hian<ImageView, *>.CENTER_INSIDE     get() = ImageView.ScaleType.CENTER_INSIDE
         val Ko5hian<ImageView, *>.FIT_CENTER        get() = ImageView.ScaleType.FIT_CENTER
         val Ko5hian<ImageView, *>.FIT_END           get() = ImageView.ScaleType.FIT_END
         val Ko5hian<ImageView, *>.FIT_START         get() = ImageView.ScaleType.FIT_START
         val Ko5hian<ImageView, *>.FIT_XY            get() = ImageView.ScaleType.FIT_XY
         val Ko5hian<ImageView, *>.MATRIX            get() = ImageView.ScaleType.MATRIX

         var ImageView.image: Drawable
            get() = drawable
            set(value) = setImageDrawable(value)
      """.trimIndent())
   }
}
