@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi

import kotlin.contracts.*

@JvmField
val relativeLayoutParamsInstantiator =
      fun () = RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.relativeLayout(
      ko5hianAction: Ko5hianParentAction<RelativeLayout, L, RelativeLayout.LayoutParams>
): RelativeLayout {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::RelativeLayout,
         relativeLayoutParamsInstantiator,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.relativeLayout(
      withName: String,
      ko5hianAction: Ko5hianParentAction<RelativeLayout, L, RelativeLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         relativeLayoutParamsInstantiator,
         ko5hianAction
   )
}

val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.START_OF            @RequiresApi(17) inline get() = RelativeLayout.START_OF
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.END_OF              @RequiresApi(17) inline get() = RelativeLayout.END_OF
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_START         @RequiresApi(17) inline get() = RelativeLayout.ALIGN_START
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_END           @RequiresApi(17) inline get() = RelativeLayout.ALIGN_END
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_PARENT_START  @RequiresApi(17) inline get() = RelativeLayout.ALIGN_PARENT_START
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_PARENT_END    @RequiresApi(17) inline get() = RelativeLayout.ALIGN_PARENT_END

val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.TRUE                inline get() = RelativeLayout.TRUE
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ABOVE               inline get() = RelativeLayout.ABOVE
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.BELOW               inline get() = RelativeLayout.BELOW
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.CENTER_HORIZONTAL   inline get() = RelativeLayout.CENTER_HORIZONTAL
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.CENTER_VERTICAL     inline get() = RelativeLayout.CENTER_VERTICAL
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.CENTER_IN_PARENT    inline get() = RelativeLayout.CENTER_IN_PARENT
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_BASELINE      inline get() = RelativeLayout.ALIGN_BASELINE
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_TOP           inline get() = RelativeLayout.ALIGN_TOP
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_BOTTOM        inline get() = RelativeLayout.ALIGN_BOTTOM
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_PARENT_TOP    inline get() = RelativeLayout.ALIGN_PARENT_TOP
val Ko5hianLayoutParams<RelativeLayout.LayoutParams>.ALIGN_PARENT_BOTTOM inline get() = RelativeLayout.ALIGN_PARENT_BOTTOM
