package ko5hian

import android.view.ViewManager
import android.widget.EditText

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.editText(
      ko5hianAction: Ko5hianAction<EditText, L>
): EditText {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::EditText,
         ko5hianAction
   )
}
