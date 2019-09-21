package ko5hian

import android.view.ViewGroup
import android.widget.RadioButton

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.radioButton(
            reuse: RadioButton = RadioButton(context),
            builderAction: Ko5hianViewBuilder<RadioButton, L>.() -> Unit
      ): RadioButton
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
