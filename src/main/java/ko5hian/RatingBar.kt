package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.RatingBar

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.radtingBar(
      ko5hianAction: Ko5hianAction<RatingBar, L>
): RatingBar {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::RatingBar,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.ratingBar(
      withName: String,
      ko5hianAction: Ko5hianAction<RatingBar, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
