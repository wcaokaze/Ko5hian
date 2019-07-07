package com.wcaokaze.ko5hian

import java.io.*
import org.gradle.api.*
import com.android.build.gradle.internal.dsl.*

internal class Ko5hianGenerator(project: Project) {
   private val ko5hianRootDir =
         project.file("${project.buildDir}/generated/source/ko5hian/")

   private val ko5hianSrcDir = File(ko5hianRootDir, "src/")

   private val projectSrcDir: File

   init {
      val android = project.extensions.getByName("android") as BaseAppModuleExtension

      val mainSrcSets = android.sourceSets.getByName("main")

      projectSrcDir = mainSrcSets.java.srcDirs.firstOrNull() ?: ko5hianSrcDir

      mainSrcSets.java.setSrcDirs(mainSrcSets.java.srcDirs + ko5hianSrcDir)
   }

   fun generate(configurations: List<Ko5hianConfiguration>) {
      val hash = configurations.joinToString(separator = "") { it.getIngredientsHash() }
      val hashFile = File(ko5hianRootDir, "hash")

      try {
         if (hashFile.readText() == hash) { return }
      } catch (e: IOException) {
         // ignore
      }

      ko5hianSrcDir.deleteRecursively()
      ko5hianSrcDir.mkdirs()

      hashFile.writeText(hash)

      val runtimeFileGenerator = RuntimeFileGenerator(projectSrcDir)
      runtimeFileGenerator.generate()

      for (config in configurations) {
         val generator = Ko5hianDslGenerator(ko5hianSrcDir, config)
         generator.writeKo5hian()
      }
   }
}
