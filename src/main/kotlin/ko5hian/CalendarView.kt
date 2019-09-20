package ko5hian

import android.view.ViewGroup
import android.widget.CalendarView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.calendarView(
            target: CalendarView? = null,
            builderAction: Ko5hianViewBuilder<CalendarView, L>.() -> Unit
      )
      = addView(target, ::CalendarView, builderAction)
