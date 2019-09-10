package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.frameLayout(
            builderAction: Ko5hianViewGroupBuilder<FrameLayout, FrameLayout.LayoutParams, L>.() -> Unit
      )
      = addView(
            ::FrameLayout,
            { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
            builderAction
      )
