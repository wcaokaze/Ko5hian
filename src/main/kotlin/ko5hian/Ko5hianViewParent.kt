package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface Ko5hianViewParent<out L : ViewGroup.LayoutParams> {
   val context: Context
   val displayDensity: Float

   fun createChildLayoutParams(): L
   fun addView(child: View)
}
