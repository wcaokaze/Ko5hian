package ko5hian

import android.view.ViewGroup
import android.widget.TimePicker

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.timePicker(
            reuse: TimePicker = TimePicker(context),
            builderAction: Ko5hianViewBuilder<TimePicker, L>.() -> Unit
      ): TimePicker
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
