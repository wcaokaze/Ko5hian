package ko5hian

import android.view.ViewGroup
import android.widget.VideoView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.videoView(
            reuse: VideoView = VideoView(context),
            builderAction: Ko5hianViewBuilder<VideoView, L>.() -> Unit
      ): VideoView
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
