@file:Suppress("UNUSED")
package ko5hian

import android.view.Gravity
import android.view.ViewGroup

val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT
val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT

val Ko5hianBuilder<*, *>.START  get() = Gravity.START
val Ko5hianBuilder<*, *>.TOP    get() = Gravity.TOP
val Ko5hianBuilder<*, *>.END    get() = Gravity.END
val Ko5hianBuilder<*, *>.BOTTOM get() = Gravity.BOTTOM
val Ko5hianBuilder<*, *>.CENTER            get() = Gravity.CENTER
val Ko5hianBuilder<*, *>.CENTER_HORIZONTAL get() = Gravity.CENTER_HORIZONTAL
val Ko5hianBuilder<*, *>.CENTER_VERTICAL   get() = Gravity.CENTER_VERTICAL
