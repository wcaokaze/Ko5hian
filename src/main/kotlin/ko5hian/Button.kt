package ko5hian

import android.view.ViewGroup
import android.widget.Button

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.button(
            target: Button? = null,
            builderAction: Ko5hianViewBuilder<Button, L>.() -> Unit
      ): Button
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(target, ::Button, builderAction)
}
