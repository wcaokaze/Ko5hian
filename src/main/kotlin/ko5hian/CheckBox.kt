package ko5hian

import android.view.ViewGroup
import android.widget.CheckBox

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.checkBox(
            reuse: CheckBox = CheckBox(context),
            builderAction: Ko5hianViewBuilder<CheckBox, L>.() -> Unit
      ): CheckBox
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
