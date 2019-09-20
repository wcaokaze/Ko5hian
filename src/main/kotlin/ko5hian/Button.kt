package ko5hian

import android.view.ViewGroup
import android.widget.Button

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.button(
            target: Button? = null,
            builderAction: Ko5hianViewBuilder<Button, L>.() -> Unit
      )
      = addView(target, ::Button, builderAction)
