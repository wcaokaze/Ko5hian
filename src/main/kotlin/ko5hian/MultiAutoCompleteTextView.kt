package ko5hian

import android.view.ViewGroup
import android.widget.MultiAutoCompleteTextView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.multiAutoCompleteTextView(
            target: MultiAutoCompleteTextView? = null,
            builderAction: Ko5hianViewBuilder<MultiAutoCompleteTextView, L>.() -> Unit
      )
      = addView(target, ::MultiAutoCompleteTextView, builderAction)
