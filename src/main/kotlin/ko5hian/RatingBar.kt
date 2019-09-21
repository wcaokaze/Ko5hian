package ko5hian

import android.view.ViewGroup
import android.widget.RatingBar

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.ratingBar(
            reuse: RatingBar = RatingBar(context),
            builderAction: Ko5hianViewBuilder<RatingBar, L>.() -> Unit
      ): RatingBar
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
