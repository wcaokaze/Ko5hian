package ko5hian

import android.content.Context
import android.view.View
import android.view.ViewGroup

import kotlin.contracts.*

@DslMarker
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.TYPE)
annotation class Ko5hianViewDslMarker

class Ko5hianViewHolder<V : View, L : ViewGroup.LayoutParams>(
    val context: Context,
    val view: V,
    val layout: L
)

class Ko5hianRoot(val context: Context)

@ExperimentalContracts
inline fun <V : View> ko5hian(context: Context, builder: Ko5hianRoot.() -> V): V {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }

    return Ko5hianRoot(context).builder()
}

inline fun <V : View> ko5hian(view: V, builder: Ko5hianViewHolder<V, *>.() -> V): V {
    val vh = Ko5hianViewHolder(view.context, view, view.layoutParams)
    vh.builder()
    return view
}

const val MATCH_PARENT = android.view.ViewGroup.LayoutParams.MATCH_PARENT
const val WRAP_CONTENT = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
