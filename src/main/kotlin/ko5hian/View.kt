@file:Suppress("UNUSED")
package ko5hian

import android.view.View
import android.view.ViewGroup

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.view(
            style: String? = null,
            reuse: View = View(context),
            builderAction: Ko5hianViewBuilder<View, L>.() -> Unit
      ): View
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(style, "view", reuse, builderAction)
}

var View.backgroundColor: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   set(value) = setBackgroundColor(value)

val Ko5hianViewBuilder<*, *>.VISIBLE:   Int get() = View.VISIBLE
val Ko5hianViewBuilder<*, *>.INVISIBLE: Int get() = View.INVISIBLE
val Ko5hianViewBuilder<*, *>.GONE:      Int get() = View.GONE
