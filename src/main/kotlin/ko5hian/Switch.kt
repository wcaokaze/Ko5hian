package ko5hian

import android.view.ViewManager
import android.widget.Switch

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.switch(
      ko5hianAction: Ko5hianAction<Switch, L>
): Switch {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::Switch,
         ko5hianAction
   )
}
