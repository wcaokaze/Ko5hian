package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TimePicker

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.timePicker(
      ko5hianAction: Ko5hianAction<TimePicker, L>
): TimePicker {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TimePicker,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.timePicker(
      withName: String,
      ko5hianAction: Ko5hianAction<TimePicker, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
