package ko5hian

import android.view.ViewGroup
import android.widget.VideoView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.videoView(
            builderAction: Ko5hianViewBuilder<VideoView, L>.() -> Unit
      )
      = addView(::VideoView, builderAction)
