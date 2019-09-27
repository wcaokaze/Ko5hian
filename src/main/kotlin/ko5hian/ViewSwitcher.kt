package ko5hian

import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.ViewSwitcher

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.viewSwitcher(
      ko5hianAction: Ko5hianParentAction<ViewSwitcher, L, FrameLayout.LayoutParams>
): ViewSwitcher {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ViewSwitcher,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
