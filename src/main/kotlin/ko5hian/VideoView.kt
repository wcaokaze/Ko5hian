package ko5hian

import android.view.ViewManager
import android.widget.VideoView

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.videoView(
      ko5hianAction: Ko5hianAction<VideoView, L>
): VideoView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::VideoView,
         ko5hianAction
   )
}
