package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.SeekBar

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.seekBar(
      ko5hianAction: Ko5hianAction<SeekBar, L>
): SeekBar {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::SeekBar,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.seekBar(
      withName: String,
      ko5hianAction: Ko5hianAction<SeekBar, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
