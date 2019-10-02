
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

Each function in Ko5hian returns a View.
```kotlin
val editText: EditText

val view = ko5hian(context) {
   linearLayout {
      view.orientation = VERTICAL

      editText = editText {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
      }
   }
}
```


Advantage
--------------------------------------------------------------------------------

### Enough fast

Sorry I don't have comparison. I guess Ko5hian is as fast as anko.


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
val Ko5hianView<LinearLayout, *>.VERTICAL get() = LinearLayout.VERTICAL
val Ko5hianView<ImageView, *>.FIT_CENTER get() = ImageView.ScaleType.FIT_CENTER
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

These extensions are quite narrow.
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


Mutate View
--------------------------------------------------------------------------------

Sometimes we want to mutate Views, instead of creating new Views.
```kotlin
val view = LinearLayout(context)

// pass a view instead of context
ko5hian(view) {
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


### RecyclerView

```kotlin
class UserViewHolder(context: Context)
      : RecyclerView.ViewHolder(LinearLayout(context))
{
   private val usernameView: TextView
   private val protectedIconView: ImageView

   fun bind(user: User) {
      usernameView.text = user.name
      protectedIconView.visibility = if (user.isProtected) { VISIBLE } else { GONE }
   }

   init {
      ko5hian {
         linearLayout {
            usernameView = textView {
            }

            protectedIconView = imageView {
               view.image = drawable(R.drawable.ic_protected)
            }
         }
      }
   }
}
```


### Separating many boring view parameters

Sometimes we have so many boring view parameters.
```kotlin
ko5hian(context) {
   linearLayout {
      layout.width  = MATCH_PARENT
      layout.height = WRAP_CONTENT
      view.orientation = HORIZONTAL

      usernameView = textView {
         layout.width  = 0
         layout.height = WRAP_CONTENT
         layout.weight = 1.0f
         layout.marginStart = 16.dip
         view.textColor = 0x313131.opaque
         view.textSizeSp = 16
         view.maxLines = 1
         view.ellipsize = TRUNCATE_AT_END
         view.text = user.name // This is the main subject but covered with too many noises!!!
      }

      protectedIconView = imageView {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.image = drawable(R.drawable.ic_protected)
         view.visibility = if (user.isProtected) { VISIBLE } else { GONE }
      }
   }
}
```

Use Ko5hian 2 times.
```kotlin
// creating views
val view = ko5hian(context) {
   linearLayout {
      usernameView = textView {
         view.text = user.name // This is the main subject but covered with too many noises!!!
      }

      protectedIconView = imageView {
         view.visibility = if (user.isProtected) { VISIBLE } else { GONE }
      }
   }
}

// configure view parameters
ko5hian(view) {
   linearLayout {
      layout.width  = MATCH_PARENT
      layout.height = WRAP_CONTENT
      view.orientation = HORIZONTAL

      textView {
         layout.width  = 0
         layout.height = WRAP_CONTENT
         layout.weight = 1.0f
         layout.marginStart = 16.dip
         view.textColor = 0x313131.opaque
         view.textSizeSp = 16
         view.maxLines = 1
         view.ellipsize = TRUNCATE_AT_END
      }

      imageView {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.image = drawable(R.drawable.ic_protected)
      }
   }
}
```

When the second `ko5hian` matches the first `ko5hian`, Ko5hian can scan
the view construction. In this example, both `ko5hian`s have the follow construction.
```kotlin
linearLayout {
   textView {}
   imageView {}
}
```

If their constructions were not matched, the second `ko5hian` would mean `addView`.

