@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.frameLayout(
            target: FrameLayout? = null,
            builderAction: Ko5hianViewGroupBuilder<FrameLayout, FrameLayout.LayoutParams, L>.() -> Unit
      ): FrameLayout
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         target,
         ::FrameLayout,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
