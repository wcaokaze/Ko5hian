package ko5hian

import android.view.ViewGroup
import android.widget.Space

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.space(
            target: Space? = null,
            builderAction: Ko5hianViewBuilder<Space, L>.() -> Unit
      ): Space
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(target, ::Space, builderAction)
}
