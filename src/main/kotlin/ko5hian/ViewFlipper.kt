package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ViewFlipper

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.viewFlipper(
            style: String? = null,
            reuse: ViewFlipper = ViewFlipper(context),
            builderAction: Ko5hianViewGroupBuilder<ViewFlipper, FrameLayout.LayoutParams, L>.() -> Unit
      ): ViewFlipper
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         style,
         "viewFlipper",
         reuse,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
