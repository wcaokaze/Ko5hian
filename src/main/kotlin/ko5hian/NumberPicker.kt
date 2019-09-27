package ko5hian

import android.view.ViewManager
import android.widget.NumberPicker

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.numberPicker(
      ko5hianAction: Ko5hianAction<NumberPicker, L>
): NumberPicker {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::NumberPicker,
         ko5hianAction
   )
}
