@file:Suppress("UNUSED")
package ko5hian

import android.view.View
import android.view.ViewGroup
import android.view.ViewManager

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
   set(value) = setBackgroundColor(value)

val Ko5hianView<View>.VISIBLE:   Int get() = View.VISIBLE
val Ko5hianView<View>.INVISIBLE: Int get() = View.INVISIBLE
val Ko5hianView<View>.GONE:      Int get() = View.GONE
