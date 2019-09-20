package ko5hian

import android.view.ViewGroup
import android.widget.Space

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.space(
            target: Space? = null,
            builderAction: Ko5hianViewBuilder<Space, L>.() -> Unit
      )
      = addView(target, ::Space, builderAction)
