package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class LinearLayoutExtensionGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "linearLayouts.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         // This file is safe to modify.

         @file:Suppress("UNUSED")
         package ko5hian

         import android.widget.LinearLayout

         val Ko5hian<LinearLayout, *>.VERTICAL   get() = LinearLayout.VERTICAL
         val Ko5hian<LinearLayout, *>.HORIZONTAL get() = LinearLayout.HORIZONTAL
      """.trimIndent())
   }
}
