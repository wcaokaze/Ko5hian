package ko5hian

import android.view.ViewGroup
import android.widget.TableLayout

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tableLayout(
            style: String? = null,
            reuse: TableLayout = TableLayout(context),
            builderAction: Ko5hianViewGroupBuilder<TableLayout, TableLayout.LayoutParams, L>.() -> Unit
      ): TableLayout
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         style,
         "tableLayout",
         reuse,
         { TableLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         builderAction
   )
}
