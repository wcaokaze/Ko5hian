package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageSwitcher

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.imageSwitcher(
            reuse: ImageSwitcher = ImageSwitcher(context),
            builderAction: Ko5hianViewGroupBuilder<ImageSwitcher, FrameLayout.LayoutParams, L>.() -> Unit
      ): ImageSwitcher
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         reuse,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
