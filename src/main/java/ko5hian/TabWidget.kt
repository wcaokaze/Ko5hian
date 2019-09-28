package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.TabWidget

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.tabWidget(
      ko5hianAction: Ko5hianParentAction<TabWidget, L, LinearLayout.LayoutParams>
): TabWidget {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TabWidget,
         { LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.tabWidget(
      withName: String,
      ko5hianAction: Ko5hianParentAction<TabWidget, L, LinearLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         { LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
