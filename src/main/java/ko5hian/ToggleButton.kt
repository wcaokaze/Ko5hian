package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ToggleButton

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.toggleButton(
      ko5hianAction: Ko5hianAction<ToggleButton, L>
): ToggleButton {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ToggleButton,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.toggleButton(
      withName: String,
      ko5hianAction: Ko5hianAction<ToggleButton, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
