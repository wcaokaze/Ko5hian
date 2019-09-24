package ko5hian

import android.view.ViewGroup
import android.widget.QuickContactBadge

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.quickContactBadge(
            style: String? = null,
            reuse: QuickContactBadge = QuickContactBadge(context),
            builderAction: Ko5hianViewBuilder<QuickContactBadge, L>.() -> Unit
      ): QuickContactBadge
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(style, "quickContactBadge", reuse, builderAction)
}
