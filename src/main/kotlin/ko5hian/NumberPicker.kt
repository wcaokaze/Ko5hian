package ko5hian

import android.view.ViewGroup
import android.widget.NumberPicker

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.numberPicker(
            target: NumberPicker? = null,
            builderAction: Ko5hianViewBuilder<NumberPicker, L>.() -> Unit
      ): NumberPicker
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(target, ::NumberPicker, builderAction)
}
