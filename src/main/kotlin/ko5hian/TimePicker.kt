package ko5hian

import android.view.ViewGroup
import android.widget.TimePicker

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.timePicker(
            target: TimePicker? = null,
            builderAction: Ko5hianViewBuilder<TimePicker, L>.() -> Unit
      )
      = addView(target, ::TimePicker, builderAction)
