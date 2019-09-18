package ko5hian

import android.content.*
import android.view.*

class Ko5hianRootViewSupplier(
      override val context: Context
) : Ko5hianViewParent<ViewGroup.LayoutParams> {
   override val displayDensity = context.resources.displayMetrics.density

   override fun createChildLayoutParams()
         = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

   override fun addView(child: View) {
      // nop
   }
}

inline fun <R> ko5hian(
      context: Context,
      builderAction: Ko5hianRootViewSupplier.() -> R
): R {
   val viewSupplier = Ko5hianRootViewSupplier(context)
   return builderAction(viewSupplier)
}
