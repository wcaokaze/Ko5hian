package ko5hian

import android.view.ViewManager
import android.widget.ProgressBar

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.progressBar(
      ko5hianAction: Ko5hianAction<ProgressBar, L>
): ProgressBar {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ProgressBar,
         ko5hianAction
   )
}
