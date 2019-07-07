package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class ViewExtensionGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "views.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         // This file is safe to modify.

         @file:Suppress("UNUSED")
         package ko5hian

         import android.view.*

         var View.backgroundColor: Int
            @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
            get() = throw UnsupportedOperationException()
            set(value) = setBackgroundColor(value)
      """.trimIndent())
   }
}
