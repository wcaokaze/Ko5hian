package ko5hian

import android.view.ViewManager
import android.widget.Chronometer

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.chronometer(
      ko5hianAction: Ko5hianAction<Chronometer, L>
): Chronometer {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::Chronometer,
         ko5hianAction
   )
}
