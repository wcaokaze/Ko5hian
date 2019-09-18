package ko5hian

import android.view.ViewGroup
import android.widget.ImageButton

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.imageButton(
            builderAction: Ko5hianViewBuilder<ImageButton, L>.() -> Unit
      )
      = addView(::ImageButton, builderAction)
