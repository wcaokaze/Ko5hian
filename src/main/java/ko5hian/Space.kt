package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Space

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.space(
      ko5hianAction: Ko5hianAction<Space, L>
): Space {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::Space,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.space(
      withName: String,
      ko5hianAction: Ko5hianAction<Space, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
