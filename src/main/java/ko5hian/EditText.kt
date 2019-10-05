package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.EditText

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.editText(
      ko5hianAction: Ko5hianAction<EditText, L>
): EditText {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::EditText,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.editText(
      withName: String,
      ko5hianAction: Ko5hianAction<EditText, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
