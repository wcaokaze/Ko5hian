package ko5hian

import android.view.ViewManager
import android.widget.Button

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.button(
      ko5hianAction: Ko5hianAction<Button, L>
): Button {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::Button,
         ko5hianAction
   )
}
