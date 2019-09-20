package ko5hian

import android.view.ViewGroup
import android.widget.NumberPicker

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.numberPicker(
            target: NumberPicker? = null,
            builderAction: Ko5hianViewBuilder<NumberPicker, L>.() -> Unit
      )
      = addView(target, ::NumberPicker, builderAction)
