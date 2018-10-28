
Ko5hian
================================================================================

Ko5hian is a DSL for Android View Layout. As you know, the most famous DSL for
Android View Layout is [anko](https://github.com/Kotlin/anko).
In Japan, "Anko" means "jam made of beans", it's often used for Japanese
confectionery. There are various types of "Anko". Especially, "Anko" made from
hashed beans is called "Koshian".


Advantage
--------------------------------------------------------------------------------

### inlined

All components in Ko5hian are `inline fun` or `typealias`.
Almost zero cost at runtime.

### lParams

anko's LayoutParams Builder is lame. Here is sample from anko's wiki page.

```kotlin
linearLayout {
   button("Login") {
      textSize = 26f
   }.lparams(width = wrapContent) {
      horizontalMargin = dip(5)
      topMargin = dip(10)
   }
}
```

Ko5hian makes it the follow:
```kotlin
linearLayout {
   button {
      lParams.width = WRAP_CONTENT
      lParams.marginStart = dip( 5)
      lParams.topMargin   = dip(10)
      lParams.marginEnd   = dip( 5)
      text = "Login"
      textSize = 26.0f
   }
}
```

Or, if you prefer, you can use the follow style:
```kotlin
linearLayout {
   button {
      lParams {
         width = WRAP_CONTENT
         marginStart = dip( 5)
         topMargin   = dip(10)
         marginEnd   = dip( 5)
      }

      text = "Login"
      textSize = 26.0f
   }
}
```

Anyway, Ko5hian is far better than anko.


Disadvantage
--------------------------------------------------------------------------------

anko has many extension functions. Ko5hian has few.

Ko5hian is released under the [WTFPL](LICENSE). Let's dirty Ko5hian's namespace!

```kotlin
package ko5hian

import android.view.*

val ViewGroup.children: Sequence<View>
   get() = (0 until childCount).asSequence().map { getChildAt(it) }

// and so on
```


Install
--------------------------------------------------------------------------------

Download ko5hian.gradle and `apply` it. Other files are unecessary.

```groovy
apply from: 'ko5hian.gradle'

ko5hian {
   viewGroups = [
      'android.widget.FrameLayout',
   ]

   views = [
      'android.view.View',
      'android.widget.FrameLayout',
      'android.widget.TextView',
   ]
}

ko5hian {
   // usually DSL is generated into 'ko5hian'. But we can change the package
   outPackage = 'your.package.widget.ko5hian'

   viewGroups = [
      'your.package.widget.YourViewGroup',

      // LayoutParams is inferred as "YourViewGroup.LayoutParams".
      // But of course we can specify another class
      [
         className: 'your.package.widget.YourViewGroup',
         lParamsClassName: 'android.widget.FrameLayout.LayoutParams'
      ],

      // LayoutParams is instantiated as
      // "LayoutParams(WRAP_CONTENT, WRAP_CONTENT)" by default.
      // We can specify another expression.
      // Ko5hian has no syntax analyzer. The expression is just embedded.
      [
         className: 'your.package.widget.YourViewGroup',
         lParamsInstantiation: 'your.package.widget.YourViewGroup.LayoutParams()'
      ],
   ]

   views = [
      'your.package.widget.YourView',

      // View is instantiated as "YourView(context)" as default.
      [
         className: 'your.package.widget.YourView',
         instantiation: 'your.package.widget.YourView(context, null)'
      ],
   ]
}
```


Theory
--------------------------------------------------------------------------------

How does Ko5hian implement lParams abstraction that anko couldn't achieved?

```kotlin
// This is the most basic function for DSL.
inline fun FrameLayout.textView(initializer: TextView.() -> Unit): TextView {
   val view = TextView(context)
   view.layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
   view.initializer()
   return view
}

fun createView() {
   frameLayout(context) {
      textView {
         // here lParams should inferred as FrameLayout.LayoutParams
         lParams.width = WRAP_CONTENT
      }
   }
}

val TextView.lParams: ??? // however we can not declare FrameLayout.LayoutParams

fun createView() {
   linearLayout(context) {
      textView {
         // since lParams should become LinearLayout.LayoutParams here
         lParams.width = WRAP_CONTENT
      }
   }
}
```


### Wrong Solution1

Pass LayoutParams to initializer.
```kotlin
inline fun FrameLayout.textView(initializer: TextView.(FrameLayout.LayoutParams) -> Unit): TextView {
   val view = TextView(context)
   val params = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
   view.layoutParams = params
   view.initializer(params)
   return view
}

fun createView() {
   frameLayout(context) {
      textView {
         // it is FrameLayout.LayoutParams
         it.width = WRAP_CONTENT
      }
   }
}
```

This works well. Except that nested `it` is shadowed.


### Wrong Solution2

Extend Views and override getLayoutParams.
```kotlin
class _TextView<P : ViewGroup.LayoutParams>(context: Context) : TextView(context) {
   override fun getLayoutParams(): P {
      @Suppress("UNCHECKED_CAST")
      return super.getLayoutParams() as P
   }
}

inline fun FrameLayout.textView(initializer: _TextView<FrameLayout.LayoutParams>.() -> Unit): TextView {
   val view = _TextView<FrameLayout.LayoutParams>(context)
   view.layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
   view.initializer()
   return view
}

fun createView() {
   frameLayout(context) {
      textView {
         // layoutParams is FrameLayout.LayoutParams
         layoutParams.width = WRAP_CONTENT
      }
   }
}
```

This way looks foolish but actually this is so close to Ko5hian.


### Ko5hian

Use `typealias`.
```kotlin
typealias _TextView<P> = TextView

inline val _TextView<FrameLayout>.lParams: FrameLayout.LayoutParams
   get() = layoutParams as FrameLayout.LayoutParams

inline fun FrameLayout.textView(initializer: _TextView<FrameLayout>.() -> Unit): TextView {
   val view = TextView(context)
   view.layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
   view.initializer()
   return view
}

fun createView() {
   frameLayout(context) {
      textView {
         // lParams is FrameLayout.LayoutParams, wow!
         lParams.width = WRAP_CONTENT
      }
   }
}
```

