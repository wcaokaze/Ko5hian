package ko5hian

import android.view.ViewGroup
import android.widget.TableRow

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tableRow(
            builderAction: Ko5hianViewBuilder<TableRow, L>.() -> Unit
      )
      = addView(::TableRow, builderAction)
