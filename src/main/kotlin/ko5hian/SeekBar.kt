package ko5hian

import android.view.ViewGroup
import android.widget.SeekBar

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.seekBar(
            target: SeekBar? = null,
            builderAction: Ko5hianViewBuilder<SeekBar, L>.() -> Unit
      )
      = addView(target, ::SeekBar, builderAction)
