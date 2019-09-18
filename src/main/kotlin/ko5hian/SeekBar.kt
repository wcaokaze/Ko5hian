package ko5hian

import android.view.ViewGroup
import android.widget.SeekBar

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.seekBar(
            builderAction: Ko5hianViewBuilder<SeekBar, L>.() -> Unit
      )
      = addView(::SeekBar, builderAction)
