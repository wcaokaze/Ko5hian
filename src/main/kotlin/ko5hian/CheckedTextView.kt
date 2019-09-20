package ko5hian

import android.view.ViewGroup
import android.widget.CheckedTextView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.checkedTextView(
            target: CheckedTextView? = null,
            builderAction: Ko5hianViewBuilder<CheckedTextView, L>.() -> Unit
      )
      = addView(target, ::CheckedTextView, builderAction)
