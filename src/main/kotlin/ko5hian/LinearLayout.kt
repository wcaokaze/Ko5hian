@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.widget.LinearLayout

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.linearLayout(
            style: String? = null,
            reuse: LinearLayout = LinearLayout(context),
            builderAction: Ko5hianViewGroupBuilder<LinearLayout, LinearLayout.LayoutParams, L>.() -> Unit
      ): LinearLayout
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         style,
         "linearLayout",
         reuse,
         { LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}

val Ko5hianViewBuilder<LinearLayout, *>.VERTICAL   get() = LinearLayout.VERTICAL
val Ko5hianViewBuilder<LinearLayout, *>.HORIZONTAL get() = LinearLayout.HORIZONTAL
