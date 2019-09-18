package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface Ko5hianBuilder<out V : View, out L : ViewGroup.LayoutParams> {
   val view: V
   val layout: L

   val context: Context
   val displayDensity: Float

   val Int.dip: Int get() {
      val px = (this * displayDensity).toInt()

      return when {
         px  != 0 -> px
         this < 0 -> -1
         else     ->  1
      }
   }
}
