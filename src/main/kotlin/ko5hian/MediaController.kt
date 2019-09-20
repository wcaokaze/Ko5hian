package ko5hian

import android.view.ViewGroup
import android.widget.MediaController

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.mediaController(
            target: MediaController? = null,
            builderAction: Ko5hianViewBuilder<MediaController, L>.() -> Unit
      )
      = addView(target, ::MediaController, builderAction)
