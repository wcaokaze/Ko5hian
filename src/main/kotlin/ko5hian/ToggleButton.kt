package ko5hian

import android.view.ViewManager
import android.widget.ToggleButton

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.toggleButton(
      ko5hianAction: Ko5hianAction<ToggleButton, L>
): ToggleButton {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ToggleButton,
         ko5hianAction
   )
}
