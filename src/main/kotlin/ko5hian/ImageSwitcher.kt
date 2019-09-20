package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageSwitcher

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.imageSwitcher(
            target: ImageSwitcher? = null,
            builderAction: Ko5hianViewGroupBuilder<ImageSwitcher, FrameLayout.LayoutParams, L>.() -> Unit
      ): ImageSwitcher
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         target,
         ::ImageSwitcher,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
