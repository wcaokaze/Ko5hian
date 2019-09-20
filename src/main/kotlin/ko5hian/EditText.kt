package ko5hian

import android.view.ViewGroup
import android.widget.EditText

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.editText(
            target: EditText? = null,
            builderAction: Ko5hianViewBuilder<EditText, L>.() -> Unit
      )
      = addView(target, ::EditText, builderAction)
