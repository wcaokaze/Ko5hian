package ko5hian

import android.view.ViewGroup
import android.widget.ToggleButton

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.toggleButton(
            reuse: ToggleButton = ToggleButton(context),
            builderAction: Ko5hianViewBuilder<ToggleButton, L>.() -> Unit
      ): ToggleButton
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
