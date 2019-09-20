package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ViewFlipper

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.viewFlipper(
            target: ViewFlipper? = null,
            builderAction: Ko5hianViewGroupBuilder<ViewFlipper, FrameLayout.LayoutParams, L>.() -> Unit
      )
      = addView(
            target,
            ::ViewFlipper,
            { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
            builderAction
      )
