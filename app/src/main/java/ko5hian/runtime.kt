package ko5hian

import android.content.Context
import android.view.View

import kotlin.contracts.*

@DslMarker
@Target(AnnotationTarget.TYPE)
annotation class Ko5hianViewDslMarker

@ExperimentalContracts
inline fun <V : View> ko5hian(context: Context, builder: Ko5hianRoot.() -> V): V {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }

    return Ko5hianRoot(context).builder()
}

const val MATCH_PARENT = android.view.ViewGroup.LayoutParams.MATCH_PARENT
const val WRAP_CONTENT = android.view.ViewGroup.LayoutParams.WRAP_CONTENT
