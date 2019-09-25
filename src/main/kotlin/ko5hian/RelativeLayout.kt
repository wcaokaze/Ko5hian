@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.widget.RelativeLayout

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.relativeLayout(
            style: String? = null,
            reuse: RelativeLayout = RelativeLayout(context),
            builderAction: Ko5hianViewGroupBuilder<RelativeLayout, RelativeLayout.LayoutParams, L>.() -> Unit
      ): RelativeLayout
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         style,
         "relativeLayout",
         reuse,
         { RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}

val Ko5hianViewBuilder<RelativeLayout, *>.TRUE                get() = RelativeLayout.TRUE
val Ko5hianViewBuilder<RelativeLayout, *>.ABOVE               get() = RelativeLayout.ABOVE
val Ko5hianViewBuilder<RelativeLayout, *>.BELOW               get() = RelativeLayout.BELOW
val Ko5hianViewBuilder<RelativeLayout, *>.CENTER_HORIZONTAL   get() = RelativeLayout.CENTER_HORIZONTAL
val Ko5hianViewBuilder<RelativeLayout, *>.CENTER_VERTICAL     get() = RelativeLayout.CENTER_VERTICAL
val Ko5hianViewBuilder<RelativeLayout, *>.CENTER_IN_PARENT    get() = RelativeLayout.CENTER_IN_PARENT
val Ko5hianViewBuilder<RelativeLayout, *>.ALIGN_BASELINE      get() = RelativeLayout.ALIGN_BASELINE
val Ko5hianViewBuilder<RelativeLayout, *>.ALIGN_TOP           get() = RelativeLayout.ALIGN_TOP
val Ko5hianViewBuilder<RelativeLayout, *>.ALIGN_BOTTOM        get() = RelativeLayout.ALIGN_BOTTOM
val Ko5hianViewBuilder<RelativeLayout, *>.ALIGN_PARENT_TOP    get() = RelativeLayout.ALIGN_PARENT_TOP
val Ko5hianViewBuilder<RelativeLayout, *>.ALIGN_PARENT_BOTTOM get() = RelativeLayout.ALIGN_PARENT_BOTTOM
