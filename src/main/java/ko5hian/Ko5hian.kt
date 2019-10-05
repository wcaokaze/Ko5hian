package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import com.wcaokaze.ko5hian.*

import kotlin.contracts.*

/**
 * Basically this instance has a View to provide Ko5hianAction.
 *
 * This is an `inline class`. Almost Zero cost on runtime.
 */
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

/**
 * View name for Ko5hian. "Scanning" Ko5hian can find Views by name.
 * ```kotlin
 * val view = ko5hian(context) {
 *    //              ^~~~~~~ Normal Ko5hian
 *    linearLayout {
 *       textView {
 *          name = "username"
 *       }
 *
 *       textView {
 *          name = "userId"
 *       }
 *    }
 * }
 *
 * ko5hian(view) {
 *    //   ^~~~ Scanning Ko5hian
 *    linearLayout {
 *       textView("userId") {
 *       }
 *    }
 * }
 * ```
 */
var Ko5hian<View, *, *>.name: String
   inline get() {
      val view = raw as View
      return view.getTag(R.id.view_tag_name) as String
   }
   inline set(value) {
      val view = raw as View
      view.setTag(R.id.view_tag_name, value)
   }

/**
 * Skip scanning to the view with the specified name.
 *
 * Note that the specified View itself is not skipped. This function moves
 * the scanning cursor to immediately before the specified View.
 */
@Suppress("nothing_to_inline")
inline fun Ko5hian<ViewGroup, *, *>.skipScanningTo(name: String) {
   Ko5hianInternal.skipScanningTo(raw as ViewGroup, name)
}

/**
 * Abort scanning children for the current ViewGroup.
 *
 * After calling this function, Ko5hian always creates new View.
 */
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

/**
 * Launch new Ko5hian without "scanning".
 */
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

/**
 * Launch new Ko5hian which does "scanning" the specified View.
 */
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

/**
 * If Ko5hian is "scanning" and the next View is an instance of `V`,
 * apply Ko5hianAction to the next View.
 * Otherwise create and add a new `V`, then apply Ko5hianAction to the new `V`.
 *
 * This is an internal function. Provide a facade function for actual use.
 * ```kotlin
 * inline fun <P : ViewManager, L> Ko5hian<P, *, L>.yourViewName(
 *       ko5hianAction: Ko5hianAction<YourViewName, L>
 * ): YourViewName {
 *    contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }
 *
 *    return addView(
 *          ::YourViewName,
 *          ko5hianAction
 *    )
 * }
 *
 * Ko5hian already have these functions for standard Views.
 * ```kotlin
 * textView {
 *    view.textColor = 0x313131.opaque
 *    view.textSizeSp = 16
 * }
 * ```
 */
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

/**
 * If Ko5hian is "scanning" and the next View is an instance of `V`,
 * apply Ko5hianAction to the next View.
 * Otherwise create and add a new `V`, then apply Ko5hianAction to the new `V`.
 *
 * This is an internal function. Provide a facade function for actual use.
 * ```kotlin
 * inline fun <P : ViewManager, L> Ko5hian<P, *, L>.yourViewGroupName(
 *       ko5hianAction: Ko5hianParentAction<YourViewGroupName, L, YourViewGroupName.LayoutParams>
 * ): YourViewGroupName {
 *    contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }
 *
 *    return addView(
 *          ::YourViewGroupName,
 *          { YourViewGroupName.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
 *          ko5hianAction
 *    )
 * }
 * ```
 *
 * Ko5hian already have these functions for standard Views.
 * ```kotlin
 * linearLayout {
 *    layout.width = MATCH_PARENT
 *    view.orientation = HORIZONTAL
 *
 *    textView {
 *       layout.width = 0
 *       layout.weight = 1.0f
 *       view.textColor = 0x313131.opaque
 *       view.textSizeSp = 16
 *    }
 *
 *    textView {
 *       view.textColor = 0x313131.opaque
 *       view.textSizeSp = 16
 *    }
 * }
 * ```
 */
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

/**
 * Search child Views by the specified name, and apply Ko5hianAction to all
 * found Views.
 *
 * This is an internal function. Provide a facade function for actual use.
 * ```kotlin
 * inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.yourViewName(
 *       withName: String,
 *       ko5hianAction: Ko5hianAction<YourViewName, L>
 * ) {
 *    contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }
 *
 *    mutateView(
 *          withName,
 *          ko5hianAction
 *    )
 * }
 * ```
 *
 * Ko5hian already have these functions for standard Views.
 * ```kotlin
 * textView("menu-item") {
 *    view.textColor = 0x313131.opaque
 *    view.textSizeSp = 16
 * }
 * ```
 */
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

/**
 * Search child Views by the specified name, and apply Ko5hianAction to all
 * found Views.
 *
 * This is an internal function. Provide a facade function for actual use.
 * ```kotlin
 * inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.yourViewGroupName(
 *       withName: String,
 *       ko5hianAction: Ko5hianParentAction<YourViewGroupName, L, YourViewGroupName.LayoutParams>
 * ) {
 *    contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }
 *
 *    mutateView(
 *          withName,
 *          { YourViewGroupName.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
 *          ko5hianAction
 *    )
 * }
 * ```
 *
 * Ko5hian already have these functions for standard Views.
 * ```kotlin
 * linearLayout("menu-item") {
 *    layout.width = MATCH_PARENT
 *    view.orientation = HORIZONTAL
 *
 *    textView {
 *       layout.width = 0
 *       layout.weight = 1.0f
 *       view.textColor = 0x313131.opaque
 *       view.textSizeSp = 16
 *    }
 *
 *    textView {
 *       view.textColor = 0x313131.opaque
 *       view.textSizeSp = 16
 *    }
 * }
 * ```
 */
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
