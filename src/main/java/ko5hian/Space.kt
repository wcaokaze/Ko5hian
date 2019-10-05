package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Space
import androidx.annotation.RequiresApi

import kotlin.contracts.*

@RequiresApi(14)
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

@RequiresApi(14)
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
