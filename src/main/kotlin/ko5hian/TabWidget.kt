package ko5hian

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TabWidget

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tabWidget(
            target: TabWidget? = null,
            builderAction: Ko5hianViewGroupBuilder<TabWidget, LinearLayout.LayoutParams, L>.() -> Unit
      )
      = addView(
            target,
            ::TabWidget,
            { LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
            builderAction
      )
