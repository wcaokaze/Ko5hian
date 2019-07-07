package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class LayoutParamsExtensionGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "layoutParams.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         // This file is safe to modify.

         @file:Suppress("UNUSED")
         package ko5hian

         import android.view.Gravity
         import android.view.ViewGroup

         val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT
         val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT

         val Ko5hian<*, *>.START  get() = Gravity.START
         val Ko5hian<*, *>.TOP    get() = Gravity.TOP
         val Ko5hian<*, *>.END    get() = Gravity.END
         val Ko5hian<*, *>.BOTTOM get() = Gravity.BOTTOM
         val Ko5hian<*, *>.CENTER            get() = Gravity.CENTER
         val Ko5hian<*, *>.CENTER_HORIZONTAL get() = Gravity.CENTER_HORIZONTAL
         val Ko5hian<*, *>.CENTER_VERTICAL   get() = Gravity.CENTER_VERTICAL
      """.trimIndent())
   }
}
