package ko5hian

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
