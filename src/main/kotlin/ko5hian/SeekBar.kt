package ko5hian

import android.view.ViewGroup
import android.widget.SeekBar

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.seekBar(
            reuse: SeekBar = SeekBar(context),
            builderAction: Ko5hianViewBuilder<SeekBar, L>.() -> Unit
      ): SeekBar
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
