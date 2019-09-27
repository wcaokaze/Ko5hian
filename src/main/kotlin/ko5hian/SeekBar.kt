package ko5hian

import android.view.ViewManager
import android.widget.SeekBar

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.seekBar(
      ko5hianAction: Ko5hianAction<SeekBar, L>
): SeekBar {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::SeekBar,
         ko5hianAction
   )
}
