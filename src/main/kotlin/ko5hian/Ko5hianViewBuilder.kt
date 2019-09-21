package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup

@Ko5hianMarker
class Ko5hianViewBuilder<out V, out L>(
      override val context: Context,
      override val view: V,
      override val layout: L,
      override val displayDensity: Float
) : Ko5hianBuilder<V, L>
      where V : View,
            L : ViewGroup.LayoutParams

inline fun <V : View, L : ViewGroup.LayoutParams> Ko5hianViewParent<L>.addView(
      reuse: V,
      builderAction: Ko5hianViewBuilder<V, L>.() -> Unit
): V {
   val layout = createChildLayoutParams()

   val builder = Ko5hianViewBuilder(context, reuse, layout, displayDensity)
   reuse.layoutParams = layout
   builder.builderAction()

   addView(reuse)

   return reuse
}
