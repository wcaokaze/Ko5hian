package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TabHost

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tabHost(
            target: TabHost? = null,
            builderAction: Ko5hianViewGroupBuilder<TabHost, FrameLayout.LayoutParams, L>.() -> Unit
      ): TabHost
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         target,
         ::TabHost,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
