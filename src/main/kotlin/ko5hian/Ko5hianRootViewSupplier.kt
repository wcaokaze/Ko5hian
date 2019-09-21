package ko5hian

import android.content.*
import android.view.*

import kotlin.contracts.*

class Ko5hianRootViewSupplier(
      override val context: Context
) : Ko5hianViewParent<ViewGroup.LayoutParams> {
   override val displayDensity = context.resources.displayMetrics.density

   override fun setLayoutParams(child: View): ViewGroup.LayoutParams {
      val l = child.layoutParams

      if (l != null) { return l }

      val newL = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
      child.layoutParams = newL
      return newL
   }

   override fun addView(child: View) {
      // nop
   }
}

@ExperimentalContracts
inline fun <R> ko5hian(
      context: Context,
      builderAction: Ko5hianRootViewSupplier.() -> R
): R {
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   val viewSupplier = Ko5hianRootViewSupplier(context)
   return builderAction(viewSupplier)
}
