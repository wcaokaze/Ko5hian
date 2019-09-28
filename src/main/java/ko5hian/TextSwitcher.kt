package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.TextSwitcher

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.textSwitcher(
      ko5hianAction: Ko5hianParentAction<TextSwitcher, L, FrameLayout.LayoutParams>
): TextSwitcher {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TextSwitcher,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.textSwitcher(
      withName: String,
      ko5hianAction: Ko5hianParentAction<TextSwitcher, L, FrameLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
