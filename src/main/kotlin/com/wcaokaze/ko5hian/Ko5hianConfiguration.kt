package com.wcaokaze.ko5hian

class Ko5hianConfiguration {
    var outPackage: String = "ko5hian"
    var viewGroups: List<ViewGroupConfiguration> = emptyList()
    var views: List<ViewConfiguration> = emptyList()

    fun viewGroups(vararg viewGroupConfiguration: ViewGroupConfiguration) {
        viewGroups = viewGroupConfiguration.toList()
    }

    fun views(vararg viewConfiguration: ViewConfiguration) {
        views = viewConfiguration.toList()
    }

    fun getIngredientsHash() = buildString {
        append(Ko5hianDslGenerator.KO5HIAN_VERSION)
        append(outPackage)

        for (vgConf in viewGroups) {
            append(vgConf.getIngredientsHash())
        }

        for (vConf in views) {
            append(vConf.getIngredientsHash())
        }
    }
}

class ViewGroupConfiguration
      internal constructor(
            val fullyClassName: String,
            val className: String,
            val lParamsFullyClassName: String,
            val lParamsInstantiatorExpression: String
      )
{
    constructor(className: String) : this(
          className,
          getClassName(className),
          getDefaultLParamsClassName(className),
          getDefaultLParamsInstantiatorExpression(
                getDefaultLParamsClassName(className))
    )

    constructor(className: String, lParamsClassName: String) : this(
          className,
          getClassName(className),
          lParamsClassName,
          getDefaultLParamsInstantiatorExpression(
                lParamsClassName)
    )

    constructor(className: String,
                lParamsClassName: String,
                lParamsInstantiation: String) : this(
          className,
          getClassName(className),
          lParamsClassName,
          lParamsInstantiation
    )

    fun getIngredientsHash() = fullyClassName + className +
          lParamsFullyClassName + lParamsInstantiatorExpression
}

class ViewConfiguration
      internal constructor(
            val fullyClassName: String,
            val className: String,
            val instantiatorExpression: String
      )
{
    constructor(className: String) : this(
          className,
          getClassName(className),
          getDefaultViewInstantiatorExpression(className)
    )

    constructor(className: String, instantiation: String) : this(
          className,
          getClassName(className),
          instantiation
    )

    fun getIngredientsHash()
          = fullyClassName + className + instantiatorExpression
}

private fun getClassName(fullyClassName: String)
        = fullyClassName.split('.').lastOrNull() ?: fullyClassName

private fun getDefaultViewInstantiatorExpression(viewFullyClassName: String)
        = "$viewFullyClassName(context)"

private fun getDefaultLParamsClassName(viewGroupFullyClassName: String)
        = "$viewGroupFullyClassName.LayoutParams"

private fun getDefaultLParamsInstantiatorExpression(lParamsFullyClassName: String)
        = "$lParamsFullyClassName(WRAP_CONTENT, WRAP_CONTENT)"
