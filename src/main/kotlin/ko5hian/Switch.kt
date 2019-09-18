package ko5hian

import android.view.ViewGroup
import android.widget.Switch

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.switch(
            builderAction: Ko5hianViewBuilder<Switch, L>.() -> Unit
      )
      = addView(::Switch, builderAction)
