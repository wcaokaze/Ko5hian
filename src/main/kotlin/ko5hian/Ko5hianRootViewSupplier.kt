package ko5hian

import android.content.*
import android.view.*

import kotlin.contracts.*

class Ko5hianRootViewSupplier(
      @JvmField override val context: Context,
      @JvmField override var style: Kss<*, *>?
) : Ko5hianViewParent<ViewGroup.LayoutParams> {
   override val displayDensity = context.resources.displayMetrics.density

   override var consumedAnonymousStyleCount = 0

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
      kss: Kss<*, *>? = null,
      builderAction: Ko5hianRootViewSupplier.() -> R
): R {
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   val viewSupplier = Ko5hianRootViewSupplier(context, kss)
   return builderAction(viewSupplier)
}
