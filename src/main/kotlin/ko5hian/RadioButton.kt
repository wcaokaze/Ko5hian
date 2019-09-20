package ko5hian

import android.view.ViewGroup
import android.widget.RadioButton

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.radioButton(
            target: RadioButton? = null,
            builderAction: Ko5hianViewBuilder<RadioButton, L>.() -> Unit
      )
      = addView(target, ::RadioButton, builderAction)
