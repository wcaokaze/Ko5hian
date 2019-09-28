package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.MediaController

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.mediaController(
      ko5hianAction: Ko5hianAction<MediaController, L>
): MediaController {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::MediaController,
         ko5hianAction
   )
}

@ExperimentalContracts
fun <P : ViewGroup, L> Ko5hian<P, *, L>.mediaController(
      withName: String,
      ko5hianAction: Ko5hianAction<MediaController, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
