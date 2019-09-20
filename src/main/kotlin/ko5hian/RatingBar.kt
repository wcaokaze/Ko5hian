package ko5hian

import android.view.ViewGroup
import android.widget.RatingBar

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.ratingBar(
            target: RatingBar? = null,
            builderAction: Ko5hianViewBuilder<RatingBar, L>.() -> Unit
      )
      = addView(target, ::RatingBar, builderAction)
