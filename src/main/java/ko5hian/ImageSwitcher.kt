package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.ImageSwitcher

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.imageSwitcher(
      ko5hianAction: Ko5hianParentAction<ImageSwitcher, L, FrameLayout.LayoutParams>
): ImageSwitcher {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ImageSwitcher,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.imageSwitcher(
      withName: String,
      ko5hianAction: Ko5hianAction<ImageSwitcher, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
