@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.LinearLayout

import kotlin.contracts.*

@JvmField
val linearLayoutParamsInstantiator =
      fun () = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.linearLayout(
      ko5hianAction: Ko5hianParentAction<LinearLayout, L, LinearLayout.LayoutParams>
): LinearLayout {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::LinearLayout,
         linearLayoutParamsInstantiator,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.linearLayout(
      withName: String,
      ko5hianAction: Ko5hianParentAction<LinearLayout, L, LinearLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         linearLayoutParamsInstantiator,
         ko5hianAction
   )
}

val Ko5hianView<LinearLayout>.VERTICAL   inline get() = LinearLayout.VERTICAL
val Ko5hianView<LinearLayout>.HORIZONTAL inline get() = LinearLayout.HORIZONTAL
