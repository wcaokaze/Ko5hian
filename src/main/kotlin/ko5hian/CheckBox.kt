package ko5hian

import android.view.ViewGroup
import android.widget.CheckBox

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.checkBox(
            target: CheckBox? = null,
            builderAction: Ko5hianViewBuilder<CheckBox, L>.() -> Unit
      )
      = addView(target, ::CheckBox, builderAction)
