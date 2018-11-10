package com.wcaokaze.ko5hian

import org.gradle.api.*

class Ko5hian implements Plugin<Project> {
    void apply(Project project) {
        def outDir = project.file("$project.buildDir/generated/source/ko5hian/src/")
        def configurations = project.container(Ko5hianConfiguration)

        configurations.all {
            def parsedConfiguration = ParsedConfigurationKt.parseConfiguration(it)

            def generator = new Ko5hianGenerator(outDir, parsedConfiguration)

            generator.writeRuntime()
            generator.writeKo5hian()
        }

        project.extensions.ko5hian = configurations

        project.android.sourceSets.main.java  .srcDirs += outDir
        project.android.sourceSets.main.kotlin.srcDirs += outDir
    }
}
