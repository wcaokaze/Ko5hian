@file:Suppress("UNUSED")
package ko5hian

import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import androidx.annotation.RequiresApi

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.view(
      ko5hianAction: Ko5hianAction<View, L>
): View {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::View,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.view(
      withName: String,
      ko5hianAction: Ko5hianAction<View, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}

var View.backgroundColor: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   inline set(value) = setBackgroundColor(value)

val Ko5hianView<View>.LAYOUT_DIRECTION_LTR: Int @RequiresApi(17) inline get() = View.LAYOUT_DIRECTION_LTR
val Ko5hianView<View>.LAYOUT_DIRECTION_RTL: Int @RequiresApi(17) inline get() = View.LAYOUT_DIRECTION_RTL

val Ko5hianView<View>.VISIBLE:   Int inline get() = View.VISIBLE
val Ko5hianView<View>.INVISIBLE: Int inline get() = View.INVISIBLE
val Ko5hianView<View>.GONE:      Int inline get() = View.GONE
