package ko5hian

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
