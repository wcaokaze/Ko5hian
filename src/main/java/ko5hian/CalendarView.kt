package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.CalendarView
import androidx.annotation.RequiresApi

import kotlin.contracts.*

@RequiresApi(11)
@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.calendarView(
      ko5hianAction: Ko5hianAction<CalendarView, L>
): CalendarView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::CalendarView,
         ko5hianAction
   )
}

@RequiresApi(11)
@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.calendarView(
      withName: String,
      ko5hianAction: Ko5hianAction<CalendarView, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
