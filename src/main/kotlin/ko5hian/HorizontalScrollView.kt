package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.HorizontalScrollView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.horizontalScrollView(
            reuse: HorizontalScrollView = HorizontalScrollView(context),
            builderAction: Ko5hianViewGroupBuilder<HorizontalScrollView, FrameLayout.LayoutParams, L>.() -> Unit
      ): HorizontalScrollView
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         reuse,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}

