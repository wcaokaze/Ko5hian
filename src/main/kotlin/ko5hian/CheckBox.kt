package ko5hian

import android.view.ViewManager
import android.widget.CheckBox

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.checkBox(
      ko5hianAction: Ko5hianAction<CheckBox, L>
): CheckBox {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::CheckBox,
         ko5hianAction
   )
}
