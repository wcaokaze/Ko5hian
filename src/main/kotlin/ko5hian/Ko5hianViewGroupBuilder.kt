package ko5hian

import android.content.*
import android.view.*

@Ko5hianMarker
class Ko5hianViewGroupBuilder<out V, out CL, out L>(
      override val context: Context,
      override val view: V,
      override val layout: L,
      override val displayDensity: Float,
      private val childLayoutParamsCreator: () -> CL
) : Ko5hianBuilder<V, L>, Ko5hianViewParent<CL>
      where V : ViewGroup,
            L : ViewGroup.LayoutParams,
            CL : ViewGroup.LayoutParams
{
   override fun createChildLayoutParams() = childLayoutParamsCreator()

   override fun addView(child: View) {
      view.addView(child)
   }
}

inline fun <V, CL, L> Ko5hianViewParent<L>.addView(
      viewConstructor: (Context) -> V,
      noinline childLayoutParamsCreator: () -> CL,
      builderAction: Ko5hianViewGroupBuilder<V, CL, L>.() -> Unit
): V
      where V : ViewGroup,
            CL : ViewGroup.LayoutParams,
            L : ViewGroup.LayoutParams
{
   val view = viewConstructor(context)
   val layout = createChildLayoutParams()

   val builder = Ko5hianViewGroupBuilder(
         context,
         view,
         layout,
         displayDensity,
         childLayoutParamsCreator)

   view.layoutParams = layout
   builder.builderAction()

   addView(view)

   return view
}
