package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup

@Ko5hianMarker
class Ko5hianViewBuilder<out V, out L>(
      override val context: Context,
      override val view: V,
      override val layout: L,
      override val displayDensity: Float
) : Ko5hianBuilder<V, L>
      where V : View,
            L : ViewGroup.LayoutParams

inline fun <V : View, L : ViewGroup.LayoutParams> Ko5hianViewParent<L>.addView(
      style: String?,
      anonymousStyleName: String,
      reuse: V,
      builderAction: Ko5hianViewBuilder<V, L>.() -> Unit
): V {
   val layout = setLayoutParams(reuse)

   val builder = Ko5hianViewBuilder(context, reuse, layout, displayDensity)

   val kss = this.style

   // this is an inline fun and `style != null` can be evaluated on compile time.
   // So the optimizer can select any of this if-branch
   if (style != null) {
      if (kss != null) {
         @Suppress("UNCHECKED_CAST")
         val child = kss.childStyles[style] as Kss<V, L>
         child.styleApplier(builder)
      }
   } else {
      run {
         if (kss == null) { return@run }

         val index = consumedAnonymousStyleCount
         val anonymousChildStyles = kss.anonymousChildStyles

         if (index >= anonymousChildStyles.size) { return@run }

         val child = anonymousChildStyles[consumedAnonymousStyleCount]

         if (child.name != anonymousStyleName) { return@run }

         consumedAnonymousStyleCount++

         @Suppress("UNCHECKED_CAST")
         (child as Kss<V, L>).styleApplier(builder)
      }
   }

   reuse.layoutParams = layout
   builder.builderAction()

   addView(reuse)

   return reuse
}
