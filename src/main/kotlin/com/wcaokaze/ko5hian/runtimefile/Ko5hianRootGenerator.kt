package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class Ko5hianRootGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "Ko5hianRoot.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         package ko5hian

         import android.content.Context

         inline class Ko5hianRoot(val context: Context)
      """.trimIndent())
   }
}
