package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TabHost

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tabHost(
            style: String? = null,
            reuse: TabHost = TabHost(context),
            builderAction: Ko5hianViewGroupBuilder<TabHost, FrameLayout.LayoutParams, L>.() -> Unit
      ): TabHost
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         style,
         "tabHost",
         reuse,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
