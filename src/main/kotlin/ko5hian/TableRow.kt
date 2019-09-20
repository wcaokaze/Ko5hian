package ko5hian

import android.view.ViewGroup
import android.widget.TableRow

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.tableRow(
            target: TableRow? = null,
            builderAction: Ko5hianViewBuilder<TableRow, L>.() -> Unit
      ): TableRow
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(target, ::TableRow, builderAction)
}
