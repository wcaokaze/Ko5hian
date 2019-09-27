@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewManager
import android.widget.FrameLayout

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.frameLayout(
      ko5hianAction: Ko5hianParentAction<FrameLayout, L, FrameLayout.LayoutParams>
): FrameLayout {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::FrameLayout,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
