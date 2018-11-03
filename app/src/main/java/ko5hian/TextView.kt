package ko5hian

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.contracts.*

@ExperimentalContracts
inline fun Ko5hianRoot.`textView`(builder: Ko5hianViewHolder<TextView, ViewGroup.LayoutParams>.() -> Unit): TextView {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }

    val v = TextView(context)
    val l = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    v.layoutParams = l
    val vh = Ko5hianViewHolder(context, v, l)
    vh.builder()
    return v
}

@ExperimentalContracts @JvmName("textViewForFrameLayout")
inline fun Ko5hianViewHolder<FrameLayout, *>.`textView`(builder: Ko5hianViewHolder<TextView, FrameLayout.LayoutParams>.() -> Unit): TextView {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }

    val v = TextView(context)
    val l = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    v.layoutParams = l
    val vh = Ko5hianViewHolder(context, v, l)
    vh.builder()
    return v
}

@ExperimentalContracts @JvmName("textViewForLinearLayout")
inline fun Ko5hianViewHolder<LinearLayout, *>.`textView`(builder: Ko5hianViewHolder<TextView, LinearLayout.LayoutParams>.() -> Unit): TextView {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }

    val v = TextView(context)
    val l = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    v.layoutParams = l
    val vh = Ko5hianViewHolder(context, v, l)
    vh.builder()
    return v
}
