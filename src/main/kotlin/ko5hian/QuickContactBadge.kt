package ko5hian

import android.view.ViewGroup
import android.widget.QuickContactBadge

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.quickContactBadge(
            builderAction: Ko5hianViewBuilder<QuickContactBadge, L>.() -> Unit
      )
      = addView(::QuickContactBadge, builderAction)
