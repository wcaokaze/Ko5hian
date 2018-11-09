package com.wcaokaze.ko5hian

import org.gradle.api.*

class Ko5hian implements Plugin<Project> {
    void apply(Project project) {
        def configurations = project.container(Ko5hianConfiguration)

        configurations.all {
            def parsedConfiguration = ParsedConfigurationKt.parseConfiguration(it)

            def generator = new Ko5hianGenerator(
                    project.file("$project.buildDir/generated/source/ko5hian/src/"),
                    parsedConfiguration)

            generator.writeRuntime()
            generator.writeKo5hian()
        }

        project.extensions.ko5hian = configurations
    }
}
