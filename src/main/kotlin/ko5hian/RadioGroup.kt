package ko5hian

import android.view.ViewGroup
import android.widget.RadioGroup

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.radioGroup(
            target: RadioGroup? = null,
            builderAction: Ko5hianViewGroupBuilder<RadioGroup, RadioGroup.LayoutParams, L>.() -> Unit
      ): RadioGroup
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         target,
         ::RadioGroup,
         { RadioGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
