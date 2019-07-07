package com.wcaokaze.ko5hian

import com.wcaokaze.ko5hian.runtimefile.*
import java.io.*

class RuntimeFileGenerator(outDir: File) {
   private val packageDir = File(outDir, "ko5hian")

   init {
      if (!packageDir.exists()) {
         packageDir.mkdirs()
      }
   }

   private val ko5hianRootFile   = File(packageDir, "Ko5hianRoot.kt")

   fun generate() {
      if (!shouldGenerate()) { return }

      sequenceOf(::Ko5hianRootGenerator,
                 ::Ko5hianMarkerGenerator,
                 ::Ko5hianRuntimeGenerator,
                 ::LayoutParamsExtensionGenerator,
                 ::ViewExtensionGenerator,
                 ::ImageViewExtensionGenerator)
            .map { it() }
            .forEach { it.generate(packageDir) }
   }

   private fun shouldGenerate() = !ko5hianRootFile.exists()
}
