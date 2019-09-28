package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Chronometer

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.chronometer(
      ko5hianAction: Ko5hianAction<Chronometer, L>
): Chronometer {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::Chronometer,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.chronometer(
      withName: String,
      ko5hianAction: Ko5hianAction<Chronometer, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
