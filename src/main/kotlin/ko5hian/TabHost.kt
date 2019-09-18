package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TabHost

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tabHost(
            builderAction: Ko5hianViewGroupBuilder<TabHost, FrameLayout.LayoutParams, L>.() -> Unit
      )
      = addView(
            ::TabHost,
            { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
            builderAction
      )
