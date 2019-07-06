package com.wcaokaze.ko5hian

import org.gradle.api.*

fun Project.ko5hian(configAction: Ko5hianConfiguratorKt.() -> Unit) {
   val configurator = Ko5hianConfiguratorKt().apply(configAction)
   Ko5hianGenerator(this).generate(configurator.configurations)
}

class Ko5hianConfiguratorKt {
   internal val configurations = ArrayList<Ko5hianConfiguration>()

   fun outputConfig(configAction: Ko5hianConfiguration.() -> Unit) {
      configurations += Ko5hianConfiguration().apply(configAction)
   }
}
