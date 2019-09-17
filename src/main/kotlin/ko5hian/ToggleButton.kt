package ko5hian

import android.view.ViewGroup
import android.widget.ToggleButton

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.toggleButton(
            builderAction: Ko5hianViewBuilder<ToggleButton, L>.() -> Unit
      )
      = addView(::ToggleButton, builderAction)
