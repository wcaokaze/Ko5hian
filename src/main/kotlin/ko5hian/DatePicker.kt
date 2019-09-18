package ko5hian

import android.view.ViewGroup
import android.widget.DatePicker

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.datePicker(
            builderAction: Ko5hianViewBuilder<DatePicker, L>.() -> Unit
      )
      = addView(::DatePicker, builderAction)
