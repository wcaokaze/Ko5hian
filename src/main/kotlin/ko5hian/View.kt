@file:Suppress("UNUSED")
package ko5hian

import android.view.View
import android.view.ViewGroup

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.view(
            target: View? = null,
            builderAction: Ko5hianViewBuilder<View, L>.() -> Unit
      )
      = addView(target, ::View, builderAction)

var View.backgroundColor: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   set(value) = setBackgroundColor(value)

val Ko5hianBuilder<*, *>.VISIBLE:   Int get() = View.VISIBLE
val Ko5hianBuilder<*, *>.INVISIBLE: Int get() = View.INVISIBLE
val Ko5hianBuilder<*, *>.GONE:      Int get() = View.GONE
