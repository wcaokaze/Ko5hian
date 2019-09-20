package ko5hian

import android.view.ViewGroup
import android.widget.ImageButton

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.imageButton(
            target: ImageButton? = null,
            builderAction: Ko5hianViewBuilder<ImageButton, L>.() -> Unit
      )
      = addView(target, ::ImageButton, builderAction)
