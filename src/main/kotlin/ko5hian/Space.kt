package ko5hian

import android.view.ViewGroup
import android.widget.Space

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.space(
            builderAction: Ko5hianViewBuilder<Space, L>.() -> Unit
      )
      = addView(::Space, builderAction)
