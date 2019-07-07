package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class ImageViewExtensionGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "imageViews.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         // This file is safe to modify.

         @file:Suppress("UNUSED")
         package ko5hian

         import android.view.Gravity
         import android.widget.ImageView
         import android.graphics.*
         import android.graphics.drawable.Drawable

         val Ko5hian<ImageView, *>.SCALE_TYPE_CENTER get() = ImageView.ScaleType.CENTER
         val Ko5hian<ImageView, *>.CENTER_CROP       get() = ImageView.ScaleType.CENTER_CROP
         val Ko5hian<ImageView, *>.CENTER_INSIDE     get() = ImageView.ScaleType.CENTER_INSIDE
         val Ko5hian<ImageView, *>.FIT_CENTER        get() = ImageView.ScaleType.FIT_CENTER
         val Ko5hian<ImageView, *>.FIT_END           get() = ImageView.ScaleType.FIT_END
         val Ko5hian<ImageView, *>.FIT_START         get() = ImageView.ScaleType.FIT_START
         val Ko5hian<ImageView, *>.FIT_XY            get() = ImageView.ScaleType.FIT_XY
         val Ko5hian<ImageView, *>.MATRIX            get() = ImageView.ScaleType.MATRIX

         var ImageView.image: Drawable?
            get() = drawable
            set(value) = setImageDrawable(value)

         var ImageView.bitmap: Bitmap?
            get() = throw UnsupportedOperationException()
      """.trimIndent())
   }
}
