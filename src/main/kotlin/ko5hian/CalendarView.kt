package ko5hian

import android.view.ViewManager
import android.widget.CalendarView

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.calendarView(
      ko5hianAction: Ko5hianAction<CalendarView, L>
): CalendarView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::CalendarView,
         ko5hianAction
   )
}
