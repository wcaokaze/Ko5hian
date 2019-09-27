@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewManager
import android.widget.TextView

import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.textView(
      ko5hianAction: Ko5hianAction<TextView, L>
): TextView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::TextView,
         ko5hianAction
   )
}

var TextView.textColor: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   set(value) = setTextColor(value)

var TextView.textSizePx: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   set(value) = setTextSize(TypedValue.COMPLEX_UNIT_PX, value.toFloat())

var TextView.textSizeDip: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   set(value) = setTextSize(TypedValue.COMPLEX_UNIT_DIP, value.toFloat())

var TextView.textSizeSp: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   set(value) = setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())

val Ko5hianView<TextView>.DEFAULT_TYPEFACE: Typeface get() = Typeface.DEFAULT
val Ko5hianView<TextView>.BOLD:             Typeface get() = Typeface.DEFAULT_BOLD
val Ko5hianView<TextView>.MONOSPACE:        Typeface get() = Typeface.MONOSPACE

val Ko5hianView<TextView>.TRUNCATE_AT_START:   TextUtils.TruncateAt get() = TextUtils.TruncateAt.START
val Ko5hianView<TextView>.TRUNCATE_AT_MARQUEE: TextUtils.TruncateAt get() = TextUtils.TruncateAt.MARQUEE
val Ko5hianView<TextView>.TRUNCATE_AT_MIDDLE:  TextUtils.TruncateAt get() = TextUtils.TruncateAt.MIDDLE
val Ko5hianView<TextView>.TRUNCATE_AT_END:     TextUtils.TruncateAt get() = TextUtils.TruncateAt.END
