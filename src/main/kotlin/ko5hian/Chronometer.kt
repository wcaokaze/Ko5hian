package ko5hian

import android.view.ViewGroup
import android.widget.Chronometer

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.chronometer(
            target: Chronometer? = null,
            builderAction: Ko5hianViewBuilder<Chronometer, L>.() -> Unit
      )
      = addView(target, ::Chronometer, builderAction)
