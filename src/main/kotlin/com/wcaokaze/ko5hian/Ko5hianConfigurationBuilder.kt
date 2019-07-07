package com.wcaokaze.ko5hian

import com.wcaokaze.ko5hian.Ko5hianConfiguration.*

class Ko5hianConfigurationBuilder {
   var outPackage: Any? = null
   var viewGroups: Any? = null
   var views:      Any? = null

   fun build(): Ko5hianConfiguration {
      val configuration = Ko5hianConfiguration()

      configuration.outPackage = parseOutPackage()
      configuration.viewGroups = parseViewGroupConf()
      configuration.views = parseViewConf()

      return configuration
   }

   private fun parseOutPackage() = outPackage as? String ?: "ko5hian"

   private fun parseViewGroupConf(): List<ViewGroupConfiguration> {
      val viewGroupConfigurations = viewGroups as? List<*> ?: return emptyList()

      return viewGroupConfigurations.mapNotNull {
         val confMap = when (it) {
            is String    -> mapOf("className" to it)
            is Map<*, *> -> it
            else         -> return@mapNotNull null
         }

         val fullyClassName = confMap["className"] as? String ?: return@mapNotNull null

         val lParamsClassName = confMap["lParamsClassName"] as? String
               ?: return@mapNotNull ViewGroupConfiguration(fullyClassName)

         val lParamsInstantiatorExpression = confMap["lParamsInstantiation"] as? String
               ?: return@mapNotNull ViewGroupConfiguration(fullyClassName, lParamsClassName)

         ViewGroupConfiguration(
               fullyClassName,
               lParamsClassName,
               lParamsInstantiatorExpression)
      }
   }

   private fun parseViewConf(): List<ViewConfiguration> {
      val viewConfigurations = views as? List<*> ?: return emptyList()

      return viewConfigurations.mapNotNull {
         val confMap = when (it) {
            is String    -> mapOf("className" to it)
            is Map<*, *> -> it
            else         -> return@mapNotNull null
         }

         val fullyClassName = confMap["className"] as? String ?: return@mapNotNull null

         val instantiatorExpression = confMap[it] as? String
               ?: return@mapNotNull ViewConfiguration(fullyClassName)

         ViewConfiguration(fullyClassName, instantiatorExpression)
      }
   }
}
