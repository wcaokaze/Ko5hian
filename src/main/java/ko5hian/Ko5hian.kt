package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import com.wcaokaze.ko5hian.*

import kotlin.contracts.*

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

var Ko5hian<View, *, *>.name: String
   inline get() {
      val view = raw as View
      return view.getTag(R.id.view_tag_name) as String
   }
   inline set(value) {
      val view = raw as View
      view.setTag(R.id.view_tag_name, value)
   }

@Suppress("nothing_to_inline")
inline fun Ko5hian<ViewGroup, *, *>.skipScanningTo(name: String) {
   Ko5hianInternal.skipScanningTo(raw as ViewGroup, name)
}

@Suppress("nothing_to_inline")
inline fun Ko5hian<ViewGroup, *, *>.skipScanningAll() {
   Ko5hianInternal.scannedIndex = -1
}

typealias Ko5hianView<V>         = Ko5hian<V, *, *>
typealias Ko5hianLayoutParams<L> = Ko5hian<*, L, *>

typealias Ko5hianAction      <V, L>     = Ko5hian<V, L, Nothing>.() -> Unit
typealias Ko5hianParentAction<V, L, CL> = Ko5hian<V, L, CL>     .() -> Unit

@JvmField
val viewGroupLayoutParamsInstantiator =
      fun () = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

@ExperimentalContracts
inline fun <R> ko5hian(
      context: Context,
      ko5hianAction: Ko5hian<Ko5hianRoot, Nothing, ViewGroup.LayoutParams>.() -> R
): R {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   val oldContext = Ko5hianInternal.context
   val oldLayoutParamsInstantiator = Ko5hianInternal.layoutParamsInstantiator
   val oldEnablesScanning = Ko5hianInternal.enablesScanning

   Ko5hianInternal.context = context
   Ko5hianInternal.layoutParamsInstantiator = viewGroupLayoutParamsInstantiator
   Ko5hianInternal.enablesScanning = false

   try {
      val ko5hianRoot = Ko5hianRoot(context)
      val ko5hian = Ko5hian<Ko5hianRoot, Nothing, ViewGroup.LayoutParams>(ko5hianRoot)
      return ko5hian.ko5hianAction()
   } finally {
      Ko5hianInternal.context = oldContext
      Ko5hianInternal.layoutParamsInstantiator = oldLayoutParamsInstantiator
      Ko5hianInternal.enablesScanning = oldEnablesScanning
   }
}

@ExperimentalContracts
inline fun <R> ko5hian(
      mutationTarget: View,
      ko5hianAction: Ko5hian<Ko5hianRoot, Nothing, ViewGroup.LayoutParams>.() -> R
): R {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   val oldContext = Ko5hianInternal.context
   val oldLayoutParamsInstantiator = Ko5hianInternal.layoutParamsInstantiator
   val oldEnablesScanning = Ko5hianInternal.enablesScanning

   Ko5hianInternal.context = mutationTarget.context
   Ko5hianInternal.layoutParamsInstantiator = viewGroupLayoutParamsInstantiator
   Ko5hianInternal.enablesScanning = true

   try {
      val ko5hianRoot = Ko5hianViewMutator(mutationTarget)
      val ko5hian = Ko5hian<Ko5hianRoot, Nothing, ViewGroup.LayoutParams>(ko5hianRoot)
      return ko5hian.ko5hianAction()
   } finally {
      Ko5hianInternal.context = oldContext
      Ko5hianInternal.layoutParamsInstantiator = oldLayoutParamsInstantiator
      Ko5hianInternal.enablesScanning = oldEnablesScanning
   }
}

inline fun <P, L, reified V> Ko5hian<P, *, L>.addView(
      viewConstructor: (Context) -> V,
      ko5hianAction: Ko5hianAction<V, L>
): V
      where P : ViewManager, V : View
{
   var view = Ko5hianInternal.findView(raw, V::class.java)

   if (view == null) {
      view = viewConstructor(Ko5hianInternal.context!!)
      Ko5hianInternal.addView(raw, view)
   } else {
      Ko5hianInternal.setLayoutParams(view)
   }

   val ko5hian = Ko5hian<V, L, Nothing>(view)
   ko5hian.ko5hianAction()

   return view
}

inline fun <P, L, reified V, CL> Ko5hian<P, *, L>.addView(
      viewConstructor: (Context) -> V,
      noinline childLayoutParamsInstantiator: () -> CL,
      ko5hianAction: Ko5hianParentAction<V, L, CL>
): V
      where P : ViewManager, V : ViewGroup
{
   var view = Ko5hianInternal.findView(raw, V::class.java)

   if (view == null) {
      view = viewConstructor(Ko5hianInternal.context!!)
      Ko5hianInternal.addView(raw, view)
   } else {
      Ko5hianInternal.setLayoutParams(view)
   }

   val oldLayoutParamsInstantiator = Ko5hianInternal.layoutParamsInstantiator
   val oldScannedIndex = Ko5hianInternal.scannedIndex

   Ko5hianInternal.layoutParamsInstantiator = childLayoutParamsInstantiator
   Ko5hianInternal.scannedIndex = if (view.childCount == 0) { -1 } else { 0 }

   val ko5hian = Ko5hian<V, L, CL>(view)
   ko5hian.ko5hianAction()

   Ko5hianInternal.layoutParamsInstantiator = oldLayoutParamsInstantiator
   Ko5hianInternal.scannedIndex = oldScannedIndex

   return view
}

inline fun <P, L, V> Ko5hian<P, *, L>.mutateView(
      name: String,
      ko5hianAction: Ko5hianAction<V, L>
)
      where P : ViewGroup, V : View
{
   val parent = raw as ViewGroup

   for (child in Ko5hianInternal.findChildrenByName(parent, name)) {
      @Suppress("UNCHECKED_CAST")
      val view = child as V

      val ko5hian = Ko5hian<V, L, Nothing>(view)
      ko5hian.ko5hianAction()
   }
}

inline fun <P, L, V, CL> Ko5hian<P, *, L>.mutateView(
      name: String,
      noinline childLayoutParamsInstantiator: () -> CL,
      ko5hianAction: Ko5hianParentAction<V, L, CL>
)
      where P : ViewManager, V : ViewGroup
{
   val parent = raw as ViewGroup

   val oldLayoutParamsInstantiator = Ko5hianInternal.layoutParamsInstantiator

   Ko5hianInternal.layoutParamsInstantiator = childLayoutParamsInstantiator

   for (child in Ko5hianInternal.findChildrenByName(parent, name)) {
      @Suppress("UNCHECKED_CAST")
      val view = child as V

      val oldScannedIndex = Ko5hianInternal.scannedIndex
      Ko5hianInternal.scannedIndex = if (view.childCount == 0) { -1 } else { 0 }

      val ko5hian = Ko5hian<V, L, Nothing>(view)
      ko5hian.ko5hianAction()

      Ko5hianInternal.scannedIndex = oldScannedIndex
   }

   Ko5hianInternal.layoutParamsInstantiator = oldLayoutParamsInstantiator
}
