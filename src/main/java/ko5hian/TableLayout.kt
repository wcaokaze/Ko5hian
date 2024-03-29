package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TableLayout

import kotlin.contracts.*

@JvmField
val tableLayoutParamsInstantiator =
      fun () = TableLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.tableLayout(
      ko5hianAction: Ko5hianParentAction<TableLayout, L, TableLayout.LayoutParams>
): TableLayout {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TableLayout,
         tableLayoutParamsInstantiator,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.tableLayout(
      withName: String,
      ko5hianAction: Ko5hianParentAction<TableLayout, L, TableLayout.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         tableLayoutParamsInstantiator,
         ko5hianAction
   )
}
