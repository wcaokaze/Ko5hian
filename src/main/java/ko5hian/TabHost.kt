package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.TabHost

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.tabHost(
      ko5hianAction: Ko5hianParentAction<TabHost, L, FrameLayout.LayoutParams>
): TabHost {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TabHost,
         frameLayoutParamsInstantiator,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.tabHost(
      withName: String,
      ko5hianAction: Ko5hianParentAction<TabHost, L, FrameLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         frameLayoutParamsInstantiator,
         ko5hianAction
   )
}
