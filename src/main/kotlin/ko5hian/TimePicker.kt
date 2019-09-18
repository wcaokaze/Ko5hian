package ko5hian

import android.view.ViewGroup
import android.widget.TimePicker

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.timePicker(
            builderAction: Ko5hianViewBuilder<TimePicker, L>.() -> Unit
      )
      = addView(::TimePicker, builderAction)
