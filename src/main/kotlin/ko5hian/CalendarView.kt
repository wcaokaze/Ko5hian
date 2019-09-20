package ko5hian

import android.view.ViewGroup
import android.widget.CalendarView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.calendarView(
            target: CalendarView? = null,
            builderAction: Ko5hianViewBuilder<CalendarView, L>.() -> Unit
      ): CalendarView
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(target, ::CalendarView, builderAction)
}
