package ko5hian

import android.view.ViewManager
import android.widget.TableRow

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.tableRow(
      ko5hianAction: Ko5hianAction<TableRow, L>
): TableRow {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TableRow,
         ko5hianAction
   )
}
