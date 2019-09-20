package ko5hian

import android.view.ViewGroup
import android.widget.Switch

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.switch(
            target: Switch? = null,
            builderAction: Ko5hianViewBuilder<Switch, L>.() -> Unit
      )
      = addView(target, ::Switch, builderAction)
