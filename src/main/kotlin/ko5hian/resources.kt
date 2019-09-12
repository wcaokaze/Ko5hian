@file:Suppress("UNUSED")
package ko5hian

import android.graphics.*
import android.graphics.drawable.*

fun Ko5hianBuilder<*, *>.drawable(resId: Int): Drawable
      = context.resources.getDrawable(resId)!!

fun Ko5hianBuilder<*, *>.drawable(resId: Int, color: Int): Drawable {
   val drawable = context.resources.getDrawable(resId)!!.mutate()
   drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)

   return drawable
}

fun Ko5hianBuilder<*, *>.string(resId: Int): String
      = context.getString(resId)

fun Ko5hianBuilder<*, *>.string(resId: Int, vararg formatArgs: Any?): String
      = context.getString(resId, *formatArgs)

val Int.opaque: Int get() = opacity(100)

/**
 * @param opacity 0..100
 */
infix fun Int.opacity(opacity: Int): Int {
   require(opacity in 0..100)

   val alpha = (0xff * (opacity / 100.0)).toInt()
   return this or (alpha shl 24)
}
