package ko5hian

import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.TextSwitcher

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.textSwitcher(
      ko5hianAction: Ko5hianParentAction<TextSwitcher, L, FrameLayout.LayoutParams>
): TextSwitcher {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TextSwitcher,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
