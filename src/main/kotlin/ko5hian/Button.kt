package ko5hian

import android.view.ViewGroup
import android.widget.Button

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.button(
            builderAction: Ko5hianViewBuilder<Button, L>.() -> Unit
      )
      = addView(::Button, builderAction)
