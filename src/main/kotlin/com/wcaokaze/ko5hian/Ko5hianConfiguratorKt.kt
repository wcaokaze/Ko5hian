package com.wcaokaze.ko5hian

import org.gradle.api.*
import com.wcaokaze.ko5hian.androidViews as internalAndroidViews
import com.wcaokaze.ko5hian.androidViewGroups as internalAndroidViewGroups

fun Project.ko5hian(configAction: Ko5hianConfiguratorKt.() -> Unit) {
   val configurator = Ko5hianConfiguratorKt().apply(configAction)
   Ko5hianGenerator(this).generate(configurator.configurations)
}

class Ko5hianConfiguratorKt {
   internal val configurations = ArrayList<Ko5hianConfiguration>()

   val androidViews      get() = internalAndroidViews
   val androidViewGroups get() = internalAndroidViewGroups

   fun outputConfig(configAction: Ko5hianConfiguration.() -> Unit) {
      configurations += Ko5hianConfiguration().apply(configAction)
   }
}
