package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class Ko5hianMarkerGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "Ko5hianMarker.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         package ko5hian

         @DslMarker
         @Retention(AnnotationRetention.SOURCE)
         @Target(AnnotationTarget.CLASS)
         annotation class Ko5hianMarker
      """.trimIndent())
   }
}
