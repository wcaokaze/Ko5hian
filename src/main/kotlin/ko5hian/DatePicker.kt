package ko5hian

import android.view.ViewManager
import android.widget.DatePicker

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.datePicker(
      ko5hianAction: Ko5hianAction<DatePicker, L>
): DatePicker {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::DatePicker,
         ko5hianAction
   )
}
