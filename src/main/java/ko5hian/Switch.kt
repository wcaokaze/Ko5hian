package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Switch

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.switch(
      ko5hianAction: Ko5hianAction<Switch, L>
): Switch {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::Switch,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.switch(
      withName: String,
      ko5hianAction: Ko5hianAction<Switch, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
