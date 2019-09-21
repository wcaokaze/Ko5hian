package ko5hian

import android.view.ViewGroup
import android.widget.ProgressBar

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.progressBar(
            reuse: ProgressBar = ProgressBar(context),
            builderAction: Ko5hianViewBuilder<ProgressBar, L>.() -> Unit
      ): ProgressBar
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
