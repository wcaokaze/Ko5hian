package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.HorizontalScrollView

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.horizontalScrollView(
      ko5hianAction: Ko5hianParentAction<FrameLayout, L, FrameLayout.LayoutParams>
): HorizontalScrollView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::HorizontalScrollView,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

@ExperimentalContracts
fun <P : ViewGroup, L> Ko5hian<P, *, L>.horizontalScrollView(
      withName: String,
      ko5hianAction: Ko5hianParentAction<HorizontalScrollView, L, FrameLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
