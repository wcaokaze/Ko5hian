package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.CheckBox

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.checkBox(
      ko5hianAction: Ko5hianAction<CheckBox, L>
): CheckBox {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::CheckBox,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.checkBox(
      withName: String,
      ko5hianAction: Ko5hianAction<CheckBox, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
