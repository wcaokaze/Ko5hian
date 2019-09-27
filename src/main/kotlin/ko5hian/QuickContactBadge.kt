package ko5hian

import android.view.ViewManager
import android.widget.QuickContactBadge

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.quickContactBadge(
      ko5hianAction: Ko5hianAction<QuickContactBadge, L>
): QuickContactBadge {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::QuickContactBadge,
         ko5hianAction
   )
}
