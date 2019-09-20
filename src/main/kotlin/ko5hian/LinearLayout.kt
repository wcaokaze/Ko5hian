@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.widget.LinearLayout

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.linearLayout(
            target: LinearLayout? = null,
            builderAction: Ko5hianViewGroupBuilder<LinearLayout, LinearLayout.LayoutParams, L>.() -> Unit
      ): LinearLayout
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         target,
         ::LinearLayout,
         { LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}

val Ko5hianBuilder<LinearLayout, *>.VERTICAL   get() = LinearLayout.VERTICAL
val Ko5hianBuilder<LinearLayout, *>.HORIZONTAL get() = LinearLayout.HORIZONTAL
