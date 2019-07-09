package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class ResourcesExtensionGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "resources.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         // This file is safe to modify.

         @file:Suppress("UNUSED")
         package ko5hian

         import android.graphics.*
         import android.graphics.drawable.*

         fun Ko5hian<*, *>.drawable(resId: Int): Drawable = context.getDrawable(resId)!!

         fun Ko5hian<*, *>.drawable(resId: Int, color: Int): Drawable {
            val drawable = context.getDrawable(resId)!!.mutate()
            drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)

            return drawable
         }

         fun Ko5hian<*, *>.string(resId: Int): String = context.getString(resId)

         fun Ko5hian<*, *>.string(resId: Int, vararg formatArgs: Any?): String
               = context.getString(resId, *formatArgs)

         val Int.opaque: Int get() = opacity(100)

         /**
          * @param opacity 0..100
          */
         infix fun Int.opacity(opacity: Int): Int {
            require(opacity in 0..100)

            val alpha = (0xff * (opacity / 100.0)).toInt()
            return this or (alpha shl 24)
         }
      """.trimIndent())
   }
}
