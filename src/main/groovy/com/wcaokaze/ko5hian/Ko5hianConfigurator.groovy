package com.wcaokaze.ko5hian

class Ko5hianConfigurator {
    def configurations = []

    def outputConfig(Closure configAction) {
        def configuration = new Ko5hianConfigurationBuilder()

        configAction.delegate = configuration
        configAction()

        configurations << configuration.build$Ko5hian_main()
    }
}
