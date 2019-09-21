package ko5hian

import android.view.ViewGroup
import android.widget.DatePicker

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.datePicker(
            reuse: DatePicker = DatePicker(context),
            builderAction: Ko5hianViewBuilder<DatePicker, L>.() -> Unit
      ): DatePicker
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
