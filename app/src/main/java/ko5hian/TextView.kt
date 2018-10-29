package ko5hian

typealias TextViewOn<P> = android.widget.TextView

val TextViewOn<android.widget.FrameLayout>.parentView: android.widget.FrameLayout get() = parent as android.widget.FrameLayout

val TextViewOn<android.widget.FrameLayout>.lParams: android.widget.FrameLayout.LayoutParams get() = layoutParams as android.widget.FrameLayout.LayoutParams

fun TextViewOn<android.widget.FrameLayout>.lParams(operation: (@Ko5hian android.widget.FrameLayout.LayoutParams).() -> Unit): android.widget.FrameLayout.LayoutParams {
    val lParams = lParams
    layoutParams = lParams.apply(operation)
    return lParams
}

inline fun android.widget.FrameLayout.`textView`(initializer: (@Ko5hian TextViewOn<android.widget.FrameLayout>).() -> Unit): TextViewOn<android.widget.FrameLayout> {
    val v = android.widget.TextView(context)
    v.layoutParams = android.widget.FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    v.initializer()
    return v
}
