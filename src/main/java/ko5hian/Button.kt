package ko5hian

import android.view.ViewGroup
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

@ExperimentalContracts
fun <P : ViewGroup, L> Ko5hian<P, *, L>.button(
      withName: String,
      ko5hianAction: Ko5hianAction<Button, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
