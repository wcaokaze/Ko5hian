package ko5hian

import android.view.ViewGroup
import android.widget.RadioGroup

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.radioGroup(
            style: String? = null,
            reuse: RadioGroup = RadioGroup(context),
            builderAction: Ko5hianViewGroupBuilder<RadioGroup, RadioGroup.LayoutParams, L>.() -> Unit
      ): RadioGroup
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         style,
         "radioGroup",
         reuse,
         { RadioGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
