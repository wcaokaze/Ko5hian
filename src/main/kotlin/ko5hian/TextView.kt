package ko5hian

import android.view.ViewGroup
import android.widget.TextView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.textView(
            builderAction: Ko5hianViewBuilder<TextView, L>.() -> Unit
      )
      = addView(::TextView, builderAction)
