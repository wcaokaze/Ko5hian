@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewManager
import android.widget.LinearLayout

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.linearLayout(
      ko5hianAction: Ko5hianParentAction<LinearLayout, L, LinearLayout.LayoutParams>
): LinearLayout {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::LinearLayout,
         { LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

val Ko5hianView<LinearLayout>.VERTICAL   get() = LinearLayout.VERTICAL
val Ko5hianView<LinearLayout>.HORIZONTAL get() = LinearLayout.HORIZONTAL
