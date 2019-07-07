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

         val Ko5hianViewHolder<*, *>.VISIBLE   get() = View.VISIBLE
         val Ko5hianViewHolder<*, *>.INVISIBLE get() = View.INVISIBLE
         val Ko5hianViewHolder<*, *>.GONE      get() = View.GONE

         val Ko5hian<*, *>.LAYOUT_DIRECTION_LTR get() = View.LAYOUT_DIRECTION_LTR
         val Ko5hian<*, *>.LAYOUT_DIRECTION_RTL get() = View.LAYOUT_DIRECTION_RTL
      """.trimIndent())
   }
}
