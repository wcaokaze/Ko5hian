package ko5hian

import android.view.ViewGroup
import android.widget.Space

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.space(
            reuse: Space = Space(context),
            builderAction: Ko5hianViewBuilder<Space, L>.() -> Unit
      ): Space
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
