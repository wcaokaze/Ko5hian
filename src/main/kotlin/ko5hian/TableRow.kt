package ko5hian

import android.view.ViewGroup
import android.widget.TableRow

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tableRow(
            target: TableRow? = null,
            builderAction: Ko5hianViewBuilder<TableRow, L>.() -> Unit
      )
      = addView(target, ::TableRow, builderAction)
