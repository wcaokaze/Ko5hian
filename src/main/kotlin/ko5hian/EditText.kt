package ko5hian

import android.view.ViewGroup
import android.widget.EditText

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.editText(
            style: String? = null,
            reuse: EditText = EditText(context),
            builderAction: Ko5hianViewBuilder<EditText, L>.() -> Unit
      ): EditText
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(style, "editText", reuse, builderAction)
}
