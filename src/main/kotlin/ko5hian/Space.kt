package ko5hian

import android.view.ViewManager
import android.widget.Space

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.space(
      ko5hianAction: Ko5hianAction<Space, L>
): Space {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::Space,
         ko5hianAction
   )
}
