package ko5hian

import android.view.ViewGroup
import android.widget.CheckedTextView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.checkedTextView(
            style: String? = null,
            reuse: CheckedTextView = CheckedTextView(context),
            builderAction: Ko5hianViewBuilder<CheckedTextView, L>.() -> Unit
      ): CheckedTextView
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(style, "checkedTextView", reuse, builderAction)
}
