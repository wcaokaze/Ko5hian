package ko5hian

import android.view.ViewManager
import android.widget.FrameLayout
import android.widget.TabHost

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.tabHost(
      ko5hianAction: Ko5hianParentAction<TabHost, L, FrameLayout.LayoutParams>
): TabHost {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TabHost,
         { FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
