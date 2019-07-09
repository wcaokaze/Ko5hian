package com.wcaokaze.ko5hian.runtimefile

import com.wcaokaze.ko5hian.*
import java.io.*

internal class Ko5hianRuntimeGenerator : RuntimeFileGenerator {
   override fun generate(packageDir: File) {
      File(packageDir, "Ko5hian.kt").writeText("""
         ${Ko5hianDslGenerator.FILE_HEADER}
         package ko5hian

         import android.content.Context
         import android.view.View
         import android.view.ViewGroup
         import androidx.recyclerview.widget.RecyclerView

         import kotlin.contracts.*

         @Ko5hianMarker
         class Ko5hian<V : View, L : ViewGroup.LayoutParams?>(
            val context: Context,
            val view: V,
            val layout: L,
            private val displayDensity: Float
         ) {
            constructor(context: Context, view: V, layout: L) : this(
                  context, view, layout,
                  context.resources.displayMetrics.density
            )

            /**
             * Sometime we have so many boring view parameters.
             * ```kotlin
             * linearLayout {
             *    textView {
             *       layout.width  = MATCH_PARENT
             *       layout.height = WRAP_CONTENT
             *       layout.marginStart = 16.dip
             *       view.textColor = 0x313131.opaque
             *       view.textSizeSp = 16
             *       view.maxLines = 1
             *       view.ellipsize = TruncateAt.END
             *       view.text = user.name // This is the main subject but covered with too many noises
             *    }
             * }
             * ```
             *
             * Extract function:
             * ```kotlin
             * fun Ko5hian<TextView, LinearLayout>.username() {
             *    layout.width  = MATCH_PARENT
             *    layout.height = WRAP_CONTENT
             *    layout.marginStart = 16.dip
             *    view.textColor = 0x313131.opaque
             *    view.textSizeSp = 16
             *    view.maxLines = 1
             *    view.ellipsize = TruncateAt.END
             * }
             * ```
             *
             * Then we can apply it like the follow:
             * ```kotlin
             * linearLayout {
             *    textView {
             *       style = ::username
             *       view.text = user.name
             *    }
             * }
             * ```
             *
             * It's very equivalent to the follow, but a little cooler notation.
             * ```kotlin
             * linearLayout {
             *    textView {
             *       username()
             *       view.text = user.name
             *    }
             * }
             * ```
             */
            var style: () -> Unit
               @Deprecated("This getter always throws an Exception")
               get() = throw UnsupportedOperationException()
               inline set(value) { value() }

            fun <CV : View, CL : ViewGroup.LayoutParams>
                  createChild(view: CV, layout: CL): Ko5hian<CV, CL>
            {
               return Ko5hian(
                     context, view, layout,
                     displayDensity
               )
            }

            val Int.dip: Int get() {
               val px = (this * displayDensity).toInt()

               return when {
                  px  != 0 -> px
                  this < 0 -> -1
                  else     ->  1
               }
            }
         }

         @ExperimentalContracts
         inline fun <V> ko5hian(context: Context, builder: Ko5hianRoot.() -> V): V {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            return Ko5hianRoot(context).builder()
         }

         @ExperimentalContracts
         inline fun <V : ViewGroup, C : View>
               V.addView(builder: Ko5hian<V, *>.() -> C): C
         {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            val vh = Ko5hian(context, this, layoutParams)

            return vh.builder()
         }

         @ExperimentalContracts @JvmName("ko5hianWithoutLParamsType")
         inline fun <V : View> ko5hian(view: V, builder: Ko5hian<V, *>.() -> Unit) {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            val vh = Ko5hian(view.context, view, view.layoutParams)

            vh.builder()
         }

         @ExperimentalContracts
         inline fun <reified V : View, reified L : ViewGroup.LayoutParams>
               ko5hian(view: View, builder: Ko5hian<V, L>.() -> Unit)
         {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            val vh = Ko5hian(view.context, view as V, view.layoutParams as L)

            vh.builder()
         }

         @ExperimentalContracts
         inline fun <reified V : View> RecyclerView.ViewHolder
               .ko5hian(builder: Ko5hian<V, RecyclerView.LayoutParams>.() -> Unit): V
         {
            contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }

            val view = itemView as V
            val layout = RecyclerView.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

            val vh = Ko5hian(view.context, view, layout)

            vh.builder()

            return view
         }
      """.trimIndent())
   }
}
