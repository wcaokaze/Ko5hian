package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.DatePicker

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.datePicker(
      ko5hianAction: Ko5hianAction<DatePicker, L>
): DatePicker {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::DatePicker,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.datePicker(
      withName: String,
      ko5hianAction: Ko5hianAction<DatePicker, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
