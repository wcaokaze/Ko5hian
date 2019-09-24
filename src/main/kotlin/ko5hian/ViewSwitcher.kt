package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ViewSwitcher

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.viewSwitcher(
            style: String? = null,
            reuse: ViewSwitcher = ViewSwitcher(context),
            builderAction: Ko5hianViewGroupBuilder<ViewSwitcher, FrameLayout.LayoutParams, L>.() -> Unit
      ): ViewSwitcher
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         style,
         "viewSwitcher",
         reuse,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
