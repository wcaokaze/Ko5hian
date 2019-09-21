package ko5hian

import android.view.ViewGroup
import android.widget.Switch

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.switch(
            reuse: Switch = Switch(context),
            builderAction: Ko5hianViewBuilder<Switch, L>.() -> Unit
      ): Switch
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
