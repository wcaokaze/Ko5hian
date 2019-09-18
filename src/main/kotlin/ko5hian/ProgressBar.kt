package ko5hian

import android.view.ViewGroup
import android.widget.ProgressBar

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.progressBar(
            builderAction: Ko5hianViewBuilder<ProgressBar, L>.() -> Unit
      )
      = addView(::ProgressBar, builderAction)
