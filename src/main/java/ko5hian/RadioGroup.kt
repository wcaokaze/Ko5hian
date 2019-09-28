package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.RadioGroup

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.radioGroup(
      ko5hianAction: Ko5hianParentAction<RadioGroup, L, RadioGroup.LayoutParams>
): RadioGroup {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::RadioGroup,
         { RadioGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

@ExperimentalContracts
fun <P : ViewGroup, L> Ko5hian<P, *, L>.radioGroup(
      withName: String,
      ko5hianAction: Ko5hianParentAction<RadioGroup, L, RadioGroup.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         { RadioGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
