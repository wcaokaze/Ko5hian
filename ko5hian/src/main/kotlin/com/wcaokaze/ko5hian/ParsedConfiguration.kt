package com.wcaokaze.ko5hian

internal class ParsedConfiguration(
    val outPackage: String,
    val viewGroups: List<ViewGroupConfiguration>,
    val views: List<ViewConfiguration>
) {
    class ViewGroupConfiguration(
        val fullyClassName: String,
        val className: String,
        val lParamsClassName: String,
        val lParamsInstantiatorExpression: String
    )

    class ViewConfiguration(
        val fullyClassName: String,
        val className: String,
        val instantiatorExpression: String
    )
}

internal fun parseConfiguration(conf: Ko5hianConfiguration): ParsedConfiguration {
    val outPackage = parseOutPackage(conf)
    val viewGroups = parseViewGroupConf(conf)
    val views = parseViewConf(conf)

    return ParsedConfiguration(outPackage, viewGroups, views)
}

private fun parseOutPackage(conf: Ko5hianConfiguration)
        = conf.outPackage as? String ?: conf.name

private fun parseViewGroupConf(conf: Ko5hianConfiguration)
        : List<ParsedConfiguration.ViewGroupConfiguration>
{
    val viewGroupConfigurations
            = conf.viewGroups as? List<*> ?: return emptyList()

    return viewGroupConfigurations.map {
        val confMap = when (it) {
            is String    -> mapOf("className" to it)
            is Map<*, *> -> it
            else         -> return@map null
        }

        val fullyClassName = confMap["className"] as? String ?: return@map null

        val className = fullyClassName.split('.').lastOrNull() ?: return@map null

        val lParamsClassName = confMap["lParamsClassName"] as? String
            ?: "$fullyClassName.LayoutParams"

        val lParamsInstantiatorExpression = confMap["lParamsInstantiation"] as? String
            ?: "$lParamsClassName(WRAP_CONTENT, WRAP_CONTENT)"

        ParsedConfiguration.ViewGroupConfiguration(
            fullyClassName, className,
            lParamsClassName, lParamsInstantiatorExpression)
    } .filterNotNull()
}

private fun parseViewConf(conf: Ko5hianConfiguration)
        : List<ParsedConfiguration.ViewConfiguration>
{
    val viewConfigurations = conf.views as? List<*> ?: return emptyList()

    return viewConfigurations.map {
        val confMap = when (it) {
            is String    -> mapOf("className" to it)
            is Map<*, *> -> it
            else         -> return@map null
        }

        val fullyClassName = confMap["className"] as? String ?: return@map null

        val className = fullyClassName.split('.').lastOrNull() ?: return@map null

        val instantiatorExpression = confMap[it] as? String
                ?: "$fullyClassName(context)"

        ParsedConfiguration.ViewConfiguration(
            fullyClassName, className, instantiatorExpression)
    } .filterNotNull()
}
