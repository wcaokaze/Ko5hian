package ko5hian

import android.view.ViewGroup
import android.widget.CalendarView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.calendarView(
            reuse: CalendarView = CalendarView(context),
            builderAction: Ko5hianViewBuilder<CalendarView, L>.() -> Unit
      ): CalendarView
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(reuse, builderAction)
}
