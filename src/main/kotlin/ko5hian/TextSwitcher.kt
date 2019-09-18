package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextSwitcher

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.textSwitcher(
            builderAction: Ko5hianViewGroupBuilder<TextSwitcher, FrameLayout.LayoutParams, L>.() -> Unit
      )
      = addView(
            ::TextSwitcher,
            { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
            builderAction
      )
