@file:Suppress("UNUSED")
package ko5hian

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup

val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT
val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT

fun Context.dip(dip: Float): Int {
   val px = (dip * resources.displayMetrics.density).toInt()

   return when {
      px != 0 -> px
      dip < 0 -> -1
      else    ->  1
   }
}

@Suppress("NOTHING_TO_INLINE") inline fun Context.dip(dip: Int)    = dip(dip.toFloat())
@Suppress("NOTHING_TO_INLINE") inline fun Context.dip(dip: Double) = dip(dip.toFloat())

@Suppress("NOTHING_TO_INLINE") inline fun View.dip(dip: Int)    = context.dip(dip)
@Suppress("NOTHING_TO_INLINE") inline fun View.dip(dip: Float)  = context.dip(dip)
@Suppress("NOTHING_TO_INLINE") inline fun View.dip(dip: Double) = context.dip(dip)

val Ko5hianBuilder<*, *>.START  get() = Gravity.START
val Ko5hianBuilder<*, *>.TOP    get() = Gravity.TOP
val Ko5hianBuilder<*, *>.END    get() = Gravity.END
val Ko5hianBuilder<*, *>.BOTTOM get() = Gravity.BOTTOM
val Ko5hianBuilder<*, *>.CENTER            get() = Gravity.CENTER
val Ko5hianBuilder<*, *>.CENTER_HORIZONTAL get() = Gravity.CENTER_HORIZONTAL
val Ko5hianBuilder<*, *>.CENTER_VERTICAL   get() = Gravity.CENTER_VERTICAL
