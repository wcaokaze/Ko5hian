package com.wcaokaze.ko5hian

import org.gradle.api.*

class Ko5hian implements Plugin<Project> {
    void apply(Project project) {
        project.metaClass.getKo5hian = {
            new Ko5hianConfigurator() {
                def outputConfig(Closure configAction) {
                    super.outputConfig(configAction)

                    new Ko5hianGenerator(project).generate(configurations)
                }
            }
        }

        project.metaClass.ko5hian = { Closure configAction ->
            def configurator = new Ko5hianConfigurator()

            configAction.delegate = configurator
            configAction()

            new Ko5hianGenerator(project).generate(configurator.configurations)
        }
    }
}
