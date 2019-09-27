package ko5hian

import android.view.ViewManager
import android.widget.RadioButton

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.radioButton(
      ko5hianAction: Ko5hianAction<RadioButton, L>
): RadioButton {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::RadioButton,
         ko5hianAction
   )
}
