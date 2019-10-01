package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.RadioGroup

import kotlin.contracts.*

@JvmField
val radioGroupLayoutParamsInstantiator =
      fun () = RadioGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.radioGroup(
      ko5hianAction: Ko5hianParentAction<RadioGroup, L, RadioGroup.LayoutParams>
): RadioGroup {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::RadioGroup,
         radioGroupLayoutParamsInstantiator,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.radioGroup(
      withName: String,
      ko5hianAction: Ko5hianParentAction<RadioGroup, L, RadioGroup.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         radioGroupLayoutParamsInstantiator,
         ko5hianAction
   )
}
