package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.NumberPicker
import androidx.annotation.RequiresApi

import kotlin.contracts.*

@RequiresApi(11)
@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.numberPicker(
      ko5hianAction: Ko5hianAction<NumberPicker, L>
): NumberPicker {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::NumberPicker,
         ko5hianAction
   )
}

@RequiresApi(11)
@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.numberPicker(
      withName: String,
      ko5hianAction: Ko5hianAction<NumberPicker, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
