package ko5hian

import android.view.ViewGroup
import android.widget.Chronometer

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.chronometer(
            builderAction: Ko5hianViewBuilder<Chronometer, L>.() -> Unit
      )
      = addView(::Chronometer, builderAction)
