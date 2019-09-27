@file:Suppress("UNUSED")
package ko5hian

import android.graphics.*
import android.graphics.drawable.*
import android.view.View

fun Ko5hianView<View>.drawable(resId: Int): Drawable
      = (raw as View).context.resources.getDrawable(resId)!!

fun Ko5hianView<View>.drawable(resId: Int, color: Int): Drawable {
   val drawable = (raw as View).context.resources.getDrawable(resId)!!.mutate()
   drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)

   return drawable
}

fun Ko5hianView<View>.string(resId: Int): String
      = (raw as View).context.getString(resId)

fun Ko5hianView<View>.string(resId: Int, vararg formatArgs: Any?): String
      = (raw as View).context.getString(resId, *formatArgs)

val Int.opaque: Int get() = opacity(100)

/**
 * @param opacity 0..100
 */
infix fun Int.opacity(opacity: Int): Int {
   require(opacity in 0..100)

   val alpha = (0xff * (opacity / 100.0)).toInt()
   return this or (alpha shl 24)
}
