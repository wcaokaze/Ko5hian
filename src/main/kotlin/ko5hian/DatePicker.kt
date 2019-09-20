package ko5hian

import android.view.ViewGroup
import android.widget.DatePicker

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.datePicker(
            target: DatePicker? = null,
            builderAction: Ko5hianViewBuilder<DatePicker, L>.() -> Unit
      ): DatePicker
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(target, ::DatePicker, builderAction)
}
