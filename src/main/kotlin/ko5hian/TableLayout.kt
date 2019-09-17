package ko5hian

import android.view.ViewGroup
import android.widget.TableLayout

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tableLayout(
            builderAction: Ko5hianViewGroupBuilder<TableLayout, TableLayout.LayoutParams, L>.() -> Unit
      )
      = addView(
            ::TableLayout,
            { TableLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
            builderAction
      )
