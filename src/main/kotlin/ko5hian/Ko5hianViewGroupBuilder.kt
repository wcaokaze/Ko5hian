package ko5hian

import android.content.*
import android.view.*

@Ko5hianMarker
class Ko5hianViewGroupBuilder<out V, out CL, out L>(
      context: Context,
      view: V,
      layout: L,
      style: Kss<*, *>?,
      displayDensity: Float,
      private val childLayoutParamsCreator: () -> CL
) : Ko5hianViewBuilder<V, L>(context, view, layout, style, displayDensity),
    Ko5hianViewParent<CL>
      where V : ViewGroup,
            L : ViewGroup.LayoutParams,
            CL : ViewGroup.LayoutParams
{
   override var consumedAnonymousStyleCount: Int = 0

   override fun setLayoutParams(child: View): CL {
      val l = childLayoutParamsCreator()
      child.layoutParams = l
      return l
   }

   override fun addView(child: View) {
      view.addView(child)
   }
}

inline fun <V, CL, L> Ko5hianViewParent<L>.addView(
      style: String?,
      anonymousStyleName: String,
      reuse: V,
      noinline childLayoutParamsCreator: () -> CL,
      builderAction: Ko5hianViewGroupBuilder<V, CL, L>.() -> Unit
): V
      where V : ViewGroup,
            CL : ViewGroup.LayoutParams,
            L : ViewGroup.LayoutParams
{
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
      run {
         if (kss == null) { return@run null }

         val index = consumedAnonymousStyleCount
         val anonymousChildStyles = kss.anonymousChildStyles

         if (index >= anonymousChildStyles.size) { return@run null }

         val child = anonymousChildStyles[consumedAnonymousStyleCount]

         if (child.name != anonymousStyleName) { return@run null }

         consumedAnonymousStyleCount++

         @Suppress("UNCHECKED_CAST")
         return@run child as Kss<V, L>
      }
   }

   val builder = Ko5hianViewGroupBuilder(
         context,
         reuse,
         layout,
         childKss,
         displayDensity,
         childLayoutParamsCreator)

   if (childKss != null) {
      childKss.styleApplier(builder)
   }

   reuse.layoutParams = layout
   builder.builderAction()

   addView(reuse)

   return reuse
}
