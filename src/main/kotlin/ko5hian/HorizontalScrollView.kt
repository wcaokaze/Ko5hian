package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.HorizontalScrollView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.horizontalScrollView(
            builderAction: Ko5hianViewGroupBuilder<HorizontalScrollView, FrameLayout.LayoutParams, L>.() -> Unit
      )
      = addView(
            ::HorizontalScrollView,
            { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
            builderAction
      )
