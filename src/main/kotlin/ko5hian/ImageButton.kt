package ko5hian

import android.view.ViewManager
import android.widget.ImageButton

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.imageButton(
      ko5hianAction: Ko5hianAction<ImageButton, L>
): ImageButton {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ImageButton,
         ko5hianAction
   )
}
