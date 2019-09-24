package ko5hian

import android.view.ViewGroup
import android.widget.Chronometer

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.chronometer(
            style: String? = null,
            reuse: Chronometer = Chronometer(context),
            builderAction: Ko5hianViewBuilder<Chronometer, L>.() -> Unit
      ): Chronometer
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(style, "chronometer", reuse, builderAction)
}
