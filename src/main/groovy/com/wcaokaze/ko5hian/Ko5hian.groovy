package com.wcaokaze.ko5hian

import org.gradle.api.*

class Ko5hian implements Plugin<Project> {
    private File ko5hianRootDir
    private File ko5hianSrcDir

    void apply(Project project) {
        ko5hianRootDir = project.file("$project.buildDir/generated/source/ko5hian/")
        ko5hianSrcDir = new File(ko5hianRootDir, 'src/')

        project.metaClass.getKo5hian = {
            new Ko5hianConfigurator() {
                def outputConfig(Closure configAction) {
                    super.outputConfig(configAction)
                    generateInto(configurations)
                }
            }
        }

        project.metaClass.ko5hian = { Closure configAction ->
            def configurator = new Ko5hianConfigurator()

            configAction.delegate = configurator
            configAction()

            generateInto(configurator.configurations)
        }

        project.android.sourceSets.main.java  .srcDirs += ko5hianSrcDir
        project.android.sourceSets.main.kotlin.srcDirs += ko5hianSrcDir
    }

    private def generateInto(List<Ko5hianConfiguration> configurations) {
        def parsedConfigs = configurations
                .collect { ParsedConfigurationKt.parseConfiguration(it) }

        def hash = parsedConfigs.collect { it.ingredientsHash } .join()
        def hashFile = new File(ko5hianRootDir, 'hash')

        try {
            if (hashFile.text == hash) return
        } catch (IOException ignored) {
        }

        ko5hianSrcDir.deleteDir()
        ko5hianSrcDir.mkdirs()

        hashFile.text = hash

        for (def config : configurations) {
            def parsedConfig = ParsedConfigurationKt.parseConfiguration(config)

            def generator = new Ko5hianGenerator(ko5hianSrcDir, parsedConfig)

            generator.writeRuntime()
            generator.writeKo5hian()
        }
    }
}
