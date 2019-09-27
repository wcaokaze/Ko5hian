package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager

import kotlin.contracts.*

const val VIEW_TAG_ID_LAYOUT_PARAMS_INSTANTIATOR: Int = 5056148

@Ko5hianMarker
inline class Ko5hian<out V, out L, out CL>(val raw: Any) {
   val view: V
      @Suppress("UNCHECKED_CAST")
      inline get() = raw as V

   val Int   .dip: Int inline get() = Ko5hianInternal.dipToPx(this)
   val Double.dip: Int inline get() = Ko5hianInternal.dipToPx(this)
}

val <L> Ko5hian<View, L, *>.layout: L
   @Suppress("UNCHECKED_CAST")
   inline get() = (raw as View).layoutParams as L

typealias Ko5hianView<V>         = Ko5hian<V, *, *>
typealias Ko5hianLayoutParams<L> = Ko5hian<*, L, *>

typealias Ko5hianAction      <V, L>     = Ko5hian<V, L, Nothing>.() -> Unit
typealias Ko5hianParentAction<V, L, CL> = Ko5hian<V, L, CL>     .() -> Unit

@ExperimentalContracts
inline fun <R> ko5hian(
      context: Context,
      ko5hianAction: Ko5hian<Ko5hianRoot, Nothing, ViewGroup.LayoutParams>.() -> R
): R {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   val ko5hianRoot = Ko5hianRoot(context)
   val ko5hian = Ko5hian<Ko5hianRoot, Nothing, ViewGroup.LayoutParams>(ko5hianRoot)
   return ko5hian.ko5hianAction()
}

inline fun <P, L, V> Ko5hian<P, *, L>.addView(
      viewConstructor: (Context) -> V,
      ko5hianAction: Ko5hianAction<V, L>
): V
      where P : ViewManager, V : View
{
   val context = Ko5hianInternal.getContext(raw)

   val view = viewConstructor(context)
   val layout = Ko5hianInternal.createLayoutParams(raw)

   (raw as ViewManager).addView(view, layout)

   val ko5hian = Ko5hian<V, L, Nothing>(view)
   ko5hian.ko5hianAction()

   return view
}

inline fun <P, L, V, CL> Ko5hian<P, *, L>.addView(
      viewConstructor: (Context) -> V,
      noinline childLayoutParamsInstantiator: () -> CL,
      ko5hianAction: Ko5hianParentAction<V, L, CL>
): V
      where P : ViewManager, V : View
{
   val context = Ko5hianInternal.getContext(raw)

   val view = viewConstructor(context)
   val layout = Ko5hianInternal.createLayoutParams(raw)

   view.setTag(VIEW_TAG_ID_LAYOUT_PARAMS_INSTANTIATOR, childLayoutParamsInstantiator)

   (raw as ViewManager).addView(view, layout)

   val ko5hian = Ko5hian<V, L, CL>(view)
   ko5hian.ko5hianAction()

   return view
}
