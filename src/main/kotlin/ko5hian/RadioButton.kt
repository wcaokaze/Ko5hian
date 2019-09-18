package ko5hian

import android.view.ViewGroup
import android.widget.RadioButton

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.radioButton(
            builderAction: Ko5hianViewBuilder<RadioButton, L>.() -> Unit
      )
      = addView(::RadioButton, builderAction)
