package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface Ko5hianViewParent<out L : ViewGroup.LayoutParams> {
   val context: Context
   val displayDensity: Float

   var style: Kss<*, *>?

   fun setLayoutParams(child: View): L
   fun addView(child: View)

   fun getAnonymousChildStyle(anonymousChildName: String): Kss<*, *>?
}
