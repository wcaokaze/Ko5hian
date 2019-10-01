@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.FrameLayout

import kotlin.contracts.*

@JvmField
val frameLayoutParamsInstantiator =
      fun () = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.frameLayout(
      ko5hianAction: Ko5hianParentAction<FrameLayout, L, FrameLayout.LayoutParams>
): FrameLayout {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::FrameLayout,
         frameLayoutParamsInstantiator,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.frameLayout(
      withName: String,
      ko5hianAction: Ko5hianParentAction<FrameLayout, L, FrameLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         frameLayoutParamsInstantiator,
         ko5hianAction
   )
}
