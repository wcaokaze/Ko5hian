package ko5hian

import android.view.ViewGroup
import android.widget.ImageButton

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.imageButton(
            style: String? = null,
            reuse: ImageButton = ImageButton(context),
            builderAction: Ko5hianViewBuilder<ImageButton, L>.() -> Unit
      ): ImageButton
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(style, "imageButton", reuse, builderAction)
}
