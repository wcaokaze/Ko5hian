package ko5hian

import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.ViewFlipper

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.viewFlipper(
      ko5hianAction: Ko5hianParentAction<ViewFlipper, L, FrameLayout.LayoutParams>
): ViewFlipper {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ViewFlipper,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
