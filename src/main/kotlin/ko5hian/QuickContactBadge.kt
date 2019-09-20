package ko5hian

import android.view.ViewGroup
import android.widget.QuickContactBadge

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.quickContactBadge(
            target: QuickContactBadge? = null,
            builderAction: Ko5hianViewBuilder<QuickContactBadge, L>.() -> Unit
      )
      = addView(target, ::QuickContactBadge, builderAction)
