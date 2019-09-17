package ko5hian

import android.view.ViewGroup
import android.widget.EditText

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.editText(
            builderAction: Ko5hianViewBuilder<EditText, L>.() -> Unit
      )
      = addView(::EditText, builderAction)
