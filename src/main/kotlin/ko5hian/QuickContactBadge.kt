package ko5hian

import android.view.ViewGroup
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

@ExperimentalContracts
fun <P : ViewGroup, L> Ko5hian<P, *, L>.quickContactBadge(
      withName: String,
      ko5hianAction: Ko5hianAction<QuickContactBadge, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
