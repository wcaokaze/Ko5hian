package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.RadioButton

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.radioButton(
      ko5hianAction: Ko5hianAction<RadioButton, L>
): RadioButton {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::RadioButton,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.radioButton(
      withName: String,
      ko5hianAction: Ko5hianAction<RadioButton, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
