package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class RelativeLayoutExtensionGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "relativeLayouts.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         // This file is safe to modify.

         @file:Suppress("UNUSED")
         package ko5hian

         import android.widget.RelativeLayout

         val Ko5hian<RelativeLayout, *>.TRUE                get() = RelativeLayout.TRUE
         val Ko5hian<RelativeLayout, *>.START_OF            get() = RelativeLayout.START_OF
         val Ko5hian<RelativeLayout, *>.ABOVE               get() = RelativeLayout.ABOVE
         val Ko5hian<RelativeLayout, *>.END_OF              get() = RelativeLayout.END_OF
         val Ko5hian<RelativeLayout, *>.BELOW               get() = RelativeLayout.BELOW
         val Ko5hian<RelativeLayout, *>.CENTER_HORIZONTAL   get() = RelativeLayout.CENTER_HORIZONTAL
         val Ko5hian<RelativeLayout, *>.CENTER_VERTICAL     get() = RelativeLayout.CENTER_VERTICAL
         val Ko5hian<RelativeLayout, *>.CENTER_IN_PARENT    get() = RelativeLayout.CENTER_IN_PARENT
         val Ko5hian<RelativeLayout, *>.ALIGN_BASELINE      get() = RelativeLayout.ALIGN_BASELINE
         val Ko5hian<RelativeLayout, *>.ALIGN_START         get() = RelativeLayout.ALIGN_START
         val Ko5hian<RelativeLayout, *>.ALIGN_TOP           get() = RelativeLayout.ALIGN_TOP
         val Ko5hian<RelativeLayout, *>.ALIGN_END           get() = RelativeLayout.ALIGN_END
         val Ko5hian<RelativeLayout, *>.ALIGN_BOTTOM        get() = RelativeLayout.ALIGN_BOTTOM
         val Ko5hian<RelativeLayout, *>.ALIGN_PARENT_START  get() = RelativeLayout.ALIGN_PARENT_START
         val Ko5hian<RelativeLayout, *>.ALIGN_PARENT_TOP    get() = RelativeLayout.ALIGN_PARENT_TOP
         val Ko5hian<RelativeLayout, *>.ALIGN_PARENT_END    get() = RelativeLayout.ALIGN_PARENT_END
         val Ko5hian<RelativeLayout, *>.ALIGN_PARENT_BOTTOM get() = RelativeLayout.ALIGN_PARENT_BOTTOM
      """.trimIndent())
   }
}
