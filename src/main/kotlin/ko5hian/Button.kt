package ko5hian

import android.view.ViewGroup
import android.widget.Button

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.button(
            style: String? = null,
            reuse: Button = Button(context),
            builderAction: Ko5hianViewBuilder<Button, L>.() -> Unit
      ): Button
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(style, "button", reuse, builderAction)
}
