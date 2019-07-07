package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class EditTextExensionGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "editTexts.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         // This file is safe to modify.

         @file:Suppress("UNUSED")
         package ko5hian

         import android.graphics.*
         import android.widget.*
         import android.text.TextUtils.TruncateAt
         import android.util.TypedValue

         var TextView.textColor: Int
            @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
            get() = throw UnsupportedOperationException()
            set(value) = setTextColor(value)

         var TextView.textSizePx: Int
            @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
            get() = throw UnsupportedOperationException()
            set(value) = setTextSize(TypedValue.COMPLEX_UNIT_PX, value.toFloat())

         var TextView.textSizeDip: Int
            @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
            get() = throw UnsupportedOperationException()
            set(value) = setTextSize(TypedValue.COMPLEX_UNIT_DIP, value.toFloat())

         var TextView.textSizeSp: Int
            @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
            get() = throw UnsupportedOperationException()
            set(value) = setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())

         val Ko5hian<TextView, *>.DEFAULT_TYPEFACE: Typeface get() = Typeface.DEFAULT
         val Ko5hian<TextView, *>.BOLD:             Typeface get() = Typeface.DEFAULT_BOLD
         val Ko5hian<TextView, *>.MONOSPACE:        Typeface get() = Typeface.MONOSPACE

         val Ko5hian<TextView, *>.TRUNCATE_AT_START   get() = TruncateAt.START
         val Ko5hian<TextView, *>.TRUNCATE_AT_MARQUEE get() = TruncateAt.MARQUEE
         val Ko5hian<TextView, *>.TRUNCATE_AT_MIDDLE  get() = TruncateAt.MIDDLE
         val Ko5hian<TextView, *>.TRUNCATE_AT_END     get() = TruncateAt.END
      """.trimIndent())
   }
}
