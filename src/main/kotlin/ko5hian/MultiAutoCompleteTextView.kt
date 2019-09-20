package ko5hian

import android.view.ViewGroup
import android.widget.MultiAutoCompleteTextView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.multiAutoCompleteTextView(
            target: MultiAutoCompleteTextView? = null,
            builderAction: Ko5hianViewBuilder<MultiAutoCompleteTextView, L>.() -> Unit
      ): MultiAutoCompleteTextView
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(target, ::MultiAutoCompleteTextView, builderAction)
}
