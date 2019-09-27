package ko5hian

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
