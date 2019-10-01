@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TextView

import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.textView(
      ko5hianAction: Ko5hianAction<TextView, L>
): TextView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TextView,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.textView(
      withName: String,
      ko5hianAction: Ko5hianAction<TextView, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}

var TextView.textColor: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   inline set(value) = setTextColor(value)

var TextView.textSizePx: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   inline set(value) = setTextSize(TypedValue.COMPLEX_UNIT_PX, value.toFloat())

var TextView.textSizeDip: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   inline set(value) = setTextSize(TypedValue.COMPLEX_UNIT_DIP, value.toFloat())

var TextView.textSizeSp: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   inline set(value) = setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())

val Ko5hianView<TextView>.DEFAULT_TYPEFACE: Typeface inline get() = Typeface.DEFAULT
val Ko5hianView<TextView>.BOLD:             Typeface inline get() = Typeface.DEFAULT_BOLD
val Ko5hianView<TextView>.MONOSPACE:        Typeface inline get() = Typeface.MONOSPACE

val Ko5hianView<TextView>.TRUNCATE_AT_START:   TextUtils.TruncateAt inline get() = TextUtils.TruncateAt.START
val Ko5hianView<TextView>.TRUNCATE_AT_MARQUEE: TextUtils.TruncateAt inline get() = TextUtils.TruncateAt.MARQUEE
val Ko5hianView<TextView>.TRUNCATE_AT_MIDDLE:  TextUtils.TruncateAt inline get() = TextUtils.TruncateAt.MIDDLE
val Ko5hianView<TextView>.TRUNCATE_AT_END:     TextUtils.TruncateAt inline get() = TextUtils.TruncateAt.END
