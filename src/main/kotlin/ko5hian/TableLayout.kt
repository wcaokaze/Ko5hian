package ko5hian

import android.view.ViewManager
import android.widget.TableLayout

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.tableLayout(
      ko5hianAction: Ko5hianParentAction<TableLayout, L, TableLayout.LayoutParams>
): TableLayout {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TableLayout,
         { TableLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
