package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TableRow

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.tableRow(
      ko5hianAction: Ko5hianAction<TableRow, L>
): TableRow {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TableRow,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.tableRow(
      withName: String,
      ko5hianAction: Ko5hianAction<TableRow, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
