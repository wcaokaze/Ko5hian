package ko5hian

import android.view.ViewGroup
import android.widget.MediaController

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.mediaController(
            reuse: MediaController = MediaController(context),
            builderAction: Ko5hianViewBuilder<MediaController, L>.() -> Unit
      ): MediaController
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
