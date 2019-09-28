package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ProgressBar

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.progressBar(
      ko5hianAction: Ko5hianAction<ProgressBar, L>
): ProgressBar {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ProgressBar,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.progressBar(
      withName: String,
      ko5hianAction: Ko5hianAction<ProgressBar, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
