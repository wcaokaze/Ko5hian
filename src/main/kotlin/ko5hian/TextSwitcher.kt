package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextSwitcher

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.textSwitcher(
            reuse: TextSwitcher = TextSwitcher(context),
            builderAction: Ko5hianViewGroupBuilder<TextSwitcher, FrameLayout.LayoutParams, L>.() -> Unit
      ): TextSwitcher
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         reuse,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
