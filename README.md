
Ko5hian
================================================================================

Ko5hian is a DSL for Android View Layout. As you know, the most famous DSL for
Android View Layout is [anko](https://github.com/Kotlin/anko).
In Japan, "Anko" means "jam made of beans", it's often used for Japanese
confectionery. There are various types of "Anko". Especially, "Anko" made from
hashed beans is called "Koshian".


Sample
--------------------------------------------------------------------------------

Nothing is new.
```kotlin
val view = ko5hian(context) {
   linearLayout {
      view.orientation = VERTICAL

      textView {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.text = string(R.string.greeting)
      }

      editText {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
      }
   }
}
```

RecyclerView
```kotlin
class ViewHolder(context: Context) : RecyclerView.ViewHolder(LinearLayout(context)) {
   // Ko5hian is supported by Contracts. Properties can be declared with `val`.
   private val usernameView: TextView

   init {
      ko5hian(itemView as LinearLayout) {
         view.orientation = VERTICAL

         usernameView = textView {
            layout.width  = WRAP_CONTENT
            layout.height = WRAP_CONTENT
         }
      }
   }
}
```


Advantage
--------------------------------------------------------------------------------

### Enough fast

Sorry I don't have comparison. I guess Ko5hian is much faster than
LayoutInflater, but slower than Anko.


### Readable

anko's LayoutParams Builder is uncool. Here is sample from anko's wiki page.

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

Ko5hian:
```kotlin
linearLayout {
   button {
      layout.width = WRAP_CONTENT
      layout.marginStart =  5.dip
      layout.topMargin   = 10.dip
      layout.marginEnd   =  5.dip
      view.text = "Login"
      view.textSize = 26.0f
   }
}
```

Perhaps you already noticed. Yes, Ko5hian let us write `5.dip`! Say goodbye to
`dip(5)`.


### Quite narrow scoped Extension Constants

We can add extensions like the follow.
```kotlin
val Ko5hianBuilder<LinearLayout, *>.VERTICAL get() = LinearLayout.VERTICAL
val Ko5hianBuilder<ImageView, *>.FIT_CENTER get() = ImageView.ScaleType.FIT_CENTER
```

```kotlin
ko5hian(context) {
   linearLayout {
      layout.width  = MATCH_PARENT
      layout.height = MATCH_PARENT
      view.orientation = VERTICAL

      imageView {
         layout.width  = 24.dip
         layout.height = 24.dip
         view.scaleType = FIT_CENTER
         view.image = drawable(R.drawable.ic_back)
      }
   }
}
```

`Ko5hianBuilder` is annotated with a
[DslMarker](http://kotlinlang.org/docs/reference/type-safe-builders.html#scope-control-dslmarker-since-11).
So in `imageView`, `VERTICAL` is not available.

```kotlin
ko5hian(context) {
   linearLayout {
      val foo = VERTICAL // ok

      imageView {
         val bar = VERTICAL // compile error!
      }
   }
}
```


KSS
--------------------------------------------------------------------------------

Sometime we have so many boring view parameters.
```kotlin
val view = ko5hian(context) {
   linearLayout {
      layout.width  = MATCH_PARENT
      layout.height = WRAP_CONTENT
      view.orientation = HORIZONTAL

      textView {
         layout.width  = MATCH_PARENT
         layout.height = WRAP_CONTENT
         layout.marginStart = 16.dip
         view.textColor = 0x313131.opaque
         view.textSizeSp = 16
         view.maxLines = 1
         view.ellipsize = TruncateAt.END
         view.text = user.name // This is the main subject but covered with too many noises
      }

      imageView {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.image = drawable(R.drawable.ic_locked)
         view.visibility = if (user.isProtected) { VISIBLE } else { GONE }
      }
   }
}
```

Use KSS (Ko5hian Style Sheet).

```kotlin
// concentrate construction views
val kss = kss {
   linearLayout("username-container") {
      layout.width  = MATCH_PARENT
      layout.height = WRAP_CONTENT
      view.orientation = HORIZONTAL

      textView("username") {
         layout.width  = MATCH_PARENT
         layout.height = WRAP_CONTENT
         layout.marginStart = 16.dip
         view.textColor = 0x313131.opaque
         view.textSizeSp = 16
         view.maxLines = 1
         view.ellipsize = TruncateAt.END
      }

      imageView("protected-icon") {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.image = drawable(R.drawable.ic_protected)
      }
   }
}

// concentrate injection main subject
val view = ko5hian(context) {
   linearLayout("username-container") {
      style = kss

      textView("username") {
         view.text = user.name
      }

      imageView("lock-icon") {
         view.visibility = if (user.isProtected) { VISIBLE } else { GONE }
      }
   }
}
```

We can omit style names.
```kotlin
val kss = kss {
   linearLayout {
      layout.width  = MATCH_PARENT
      layout.height = WRAP_CONTENT
      view.orientation = HORIZONTAL

      textView {
         layout.width  = MATCH_PARENT
         layout.height = WRAP_CONTENT
         layout.marginStart = 16.dip
         view.textColor = 0x313131.opaque
         view.textSizeSp = 16
         view.maxLines = 1
         view.ellipsize = TruncateAt.END
      }

      imageView {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.image = drawable(R.drawable.ic_protected)
      }
   }
}

val view = ko5hian(context) {
   linearLayout {
      style = kss

      textView {
         view.text = user.name
      }

      imageView {
         view.visibility = if (user.isProtected) { VISIBLE } else { GONE }
      }
   }
}
```
