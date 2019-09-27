package ko5hian

import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.ImageSwitcher

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.imageSwitcher(
      ko5hianAction: Ko5hianParentAction<ImageSwitcher, L, FrameLayout.LayoutParams>
): ImageSwitcher {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ImageSwitcher,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
