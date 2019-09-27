package ko5hian

import android.view.ViewManager
import android.widget.RatingBar

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.radtingBar(
      ko5hianAction: Ko5hianAction<RatingBar, L>
): RatingBar {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::RatingBar,
         ko5hianAction
   )
}
