@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.RelativeLayout

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.relativeLayout(
      ko5hianAction: Ko5hianParentAction<RelativeLayout, L, RelativeLayout.LayoutParams>
): RelativeLayout {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::RelativeLayout,
         { RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

@ExperimentalContracts
fun <P : ViewGroup, L> Ko5hian<P, *, L>.relativeLayout(
      withName: String,
      ko5hianAction: Ko5hianParentAction<RelativeLayout, L, RelativeLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         { RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.TRUE                get() = RelativeLayout.TRUE
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ABOVE               get() = RelativeLayout.ABOVE
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.BELOW               get() = RelativeLayout.BELOW
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.CENTER_HORIZONTAL   get() = RelativeLayout.CENTER_HORIZONTAL
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.CENTER_VERTICAL     get() = RelativeLayout.CENTER_VERTICAL
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.CENTER_IN_PARENT    get() = RelativeLayout.CENTER_IN_PARENT
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_BASELINE      get() = RelativeLayout.ALIGN_BASELINE
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_TOP           get() = RelativeLayout.ALIGN_TOP
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_BOTTOM        get() = RelativeLayout.ALIGN_BOTTOM
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_PARENT_TOP    get() = RelativeLayout.ALIGN_PARENT_TOP
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_PARENT_BOTTOM get() = RelativeLayout.ALIGN_PARENT_BOTTOM
