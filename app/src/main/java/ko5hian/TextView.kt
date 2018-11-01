package ko5hian

import kotlin.contracts.*

typealias TextViewOn<P> = android.widget.TextView

inline val TextViewOn<android.widget.FrameLayout>.parentView: android.widget.FrameLayout get() = parent as android.widget.FrameLayout

inline val TextViewOn<android.widget.FrameLayout>.lParams: android.widget.FrameLayout.LayoutParams get() = layoutParams as android.widget.FrameLayout.LayoutParams

inline fun TextViewOn<android.widget.FrameLayout>.lParams(operation: (@Ko5hianViewDslMarker android.widget.FrameLayout.LayoutParams).() -> Unit): android.widget.FrameLayout.LayoutParams {
    val lParams = lParams
    layoutParams = lParams.apply(operation)
    return lParams
}

@ExperimentalContracts
inline fun Ko5hianRoot.`textView`(initializer: (@Ko5hianViewDslMarker android.widget.TextView).() -> Unit): android.widget.TextView {
    contract {
        callsInPlace(initializer, InvocationKind.EXACTLY_ONCE)
    }

    val v = android.widget.TextView(context)
    v.layoutParams = android.view.ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    v.initializer()
    return v
}

@ExperimentalContracts
inline fun android.widget.FrameLayout.`textView`(initializer: (@Ko5hianViewDslMarker TextViewOn<android.widget.FrameLayout>).() -> Unit): TextViewOn<android.widget.FrameLayout> {
    contract {
        callsInPlace(initializer, InvocationKind.EXACTLY_ONCE)
    }

    val v = android.widget.TextView(context)
    v.layoutParams = android.widget.FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    v.initializer()
    return v
}
