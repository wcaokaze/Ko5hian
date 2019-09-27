package ko5hian

import android.view.ViewManager
import android.widget.TimePicker

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.timePicker(
      ko5hianAction: Ko5hianAction<TimePicker, L>
): TimePicker {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TimePicker,
         ko5hianAction
   )
}
