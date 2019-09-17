package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ViewSwitcher

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.viewSwitcher(
            builderAction: Ko5hianViewGroupBuilder<ViewSwitcher, FrameLayout.LayoutParams, L>.() -> Unit
      )
      = addView(
            ::ViewSwitcher,
            { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
            builderAction
      )
