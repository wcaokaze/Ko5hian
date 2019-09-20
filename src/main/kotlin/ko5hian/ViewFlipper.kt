package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ViewFlipper

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.viewFlipper(
            target: ViewFlipper? = null,
            builderAction: Ko5hianViewGroupBuilder<ViewFlipper, FrameLayout.LayoutParams, L>.() -> Unit
      ): ViewFlipper
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         target,
         ::ViewFlipper,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
