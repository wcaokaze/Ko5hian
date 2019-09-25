package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup

@Ko5hianMarker
open class Ko5hianViewBuilder<out V : View, out L : ViewGroup.LayoutParams>(
      val context: Context,
      @JvmField val view: V,
      @JvmField val layout: L,
      var style: Kss<*, *>?,
      val displayDensity: Float
) {
   val Int.dip: Int get() {
      val px = (this * displayDensity).toInt()

      return when {
         px  != 0 -> px
         this < 0 -> -1
         else     ->  1
      }
   }
}

inline fun <V : View, L : ViewGroup.LayoutParams> Ko5hianViewParent<L>.addView(
      style: String?,
      anonymousStyleName: String,
      reuse: V,
      builderAction: Ko5hianViewBuilder<V, L>.() -> Unit
): V {
   val layout = setLayoutParams(reuse)

   val kss = this.style

   // this is an inline fun and `style != null` can be evaluated on compile time.
   // So the optimizer can select any of this if-branch
   val childKss = if (style != null) {
      if (kss == null) {
         null
      } else {
         @Suppress("UNCHECKED_CAST")
         kss.childStyles[style] as Kss<V, L>
      }
   } else {
      @Suppress("UNCHECKED_CAST")
      getAnonymousChildStyle(anonymousStyleName) as Kss<V, L>?
   }

   val builder = Ko5hianViewBuilder(context, reuse, layout, childKss, displayDensity)

   if (childKss != null) {
      childKss.styleApplier(builder)
   }

   reuse.layoutParams = layout
   builder.builderAction()

   addView(reuse)

   return reuse
}
