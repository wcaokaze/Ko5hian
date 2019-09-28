package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.MultiAutoCompleteTextView

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.multiAutoCompleteTextView(
      ko5hianAction: Ko5hianAction<MultiAutoCompleteTextView, L>
): MultiAutoCompleteTextView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::MultiAutoCompleteTextView,
         ko5hianAction
   )
}

@ExperimentalContracts
fun <P : ViewGroup, L> Ko5hian<P, *, L>.multiAutoCompleteTextView(
      withName: String,
      ko5hianAction: Ko5hianAction<MultiAutoCompleteTextView, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
