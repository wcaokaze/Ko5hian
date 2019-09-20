package ko5hian

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TabWidget

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tabWidget(
            target: TabWidget? = null,
            builderAction: Ko5hianViewGroupBuilder<TabWidget, LinearLayout.LayoutParams, L>.() -> Unit
      ): TabWidget
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         target,
         ::TabWidget,
         { LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
