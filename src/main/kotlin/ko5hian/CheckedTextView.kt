package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.CheckedTextView

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.checkedTextView(
      ko5hianAction: Ko5hianAction<CheckedTextView, L>
): CheckedTextView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::CheckedTextView,
         ko5hianAction
   )
}

@ExperimentalContracts
fun <P : ViewGroup, L> Ko5hian<P, *, L>.checkedTextView(
      withName: String,
      ko5hianAction: Ko5hianAction<CheckedTextView, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
