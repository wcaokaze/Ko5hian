package com.wcaokaze.ko5hian

import org.gradle.api.*

class Ko5hian implements Plugin<Project> {
    void apply(Project project) {
        def outDir = project.file("$project.buildDir/generated/source/ko5hian/src/")

        project.metaClass.getKo5hian = {
            new Ko5hianConfigurator() {
                def outputConfig(Closure configAction) {
                    super.outputConfig(configAction)
                    generateInto(outDir, configurations)
                }
            }
        }

        project.metaClass.ko5hian = { Closure configAction ->
            def configurator = new Ko5hianConfigurator()

            configAction.delegate = configurator
            configAction()

            generateInto(outDir, configurator.configurations)
        }

        outDir.deleteDir()
        outDir.mkdirs()

        project.android.sourceSets.main.java  .srcDirs += outDir
        project.android.sourceSets.main.kotlin.srcDirs += outDir
    }

    private def generateInto(File outDir, List<Ko5hianConfiguration> configurations) {
        for (def config : configurations) {
            def parsedConfig = ParsedConfigurationKt.parseConfiguration(config)

            def generator = new Ko5hianGenerator(outDir, parsedConfig)

            generator.writeRuntime()
            generator.writeKo5hian()
        }
    }
}
