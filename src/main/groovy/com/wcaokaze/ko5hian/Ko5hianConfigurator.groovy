package com.wcaokaze.ko5hian

class Ko5hianConfigurator {
    def configurations = []

    def outputConfig(Closure configAction) {
        def configuration = new Ko5hianConfiguration()

        configAction.delegate = configuration
        configAction()

        configurations << configuration
    }
}
