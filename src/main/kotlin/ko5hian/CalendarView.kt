package ko5hian

import android.view.ViewGroup
import android.widget.CalendarView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.calendarView(
            builderAction: Ko5hianViewBuilder<CalendarView, L>.() -> Unit
      )
      = addView(::CalendarView, builderAction)
