
Ko5hian
================================================================================

Ko5hianはAndroidのレイアウト構築用のDSLライブラリです。ご存知のように、Androidの
レイアウト構築用DSLとして最も有名なライブラリは[anko](https://github.com/Kotlin.anko)
です。
日本語で"Anko"と言うと和菓子などでよく使われる『餡』のことを指します。そして、
あんこの中でも特に豆を潰して作ったもののことをこう呼びます。

Koshian

───と。


サンプル
--------------------------------------------------------------------------------

べつに普通です。
```kotlin
val editText: EditText

val view = ko5hian(context) {
   linearLayout {
      view.orientation = VERTICAL

      textView {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.text = string(R.string.greeting)
      }

      editText = editText {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
      }
   }
}
```


長所
--------------------------------------------------------------------------------

### 十分に速い

すみません、比較をできていないのですが、おそらくKo5hianはankoと同じくらい速いと
思います。

### 読みやすい

ankoのLayoutParamsのBuilderはあまりかっこよくありません。ankoのWikiにある
サンプルがこちらです。

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

Ko5hianではこうです。
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

もうお気づきになったでしょうが、Ko5hianでは `5.dip` と書くことができます！
`dip(5)` にさよならを告げましょう。


### 絶妙に狭いスコープの定数

以下のようにして拡張を追加できます。
```kotlin
val Ko5hianView<LinearLayout>.VERTICAL get() = LinearLayout.VERTICAL
val Ko5hianView<ImageView>.FIT_CENTER get() = ImageView.ScaleType.FIT_CENTER
```

```kotlin
ko5hian(context) {
   linearLayout {
      view.orientation = VERTICAL

      imageView {
         view.scaleType = FIT_CENTER
         view.image = drawable(R.drawable.ic_back)
      }
   }
}
```

これらの拡張は非常に狭いスコープで有効です。
```kotlin
ko5hian(context) {
   linearLayout {
      val foo = VERTICAL // OK

      imageView {
         val bar = VERTICAL // コンパイルエラー！
      }
   }
}
```


子Viewのスキャン
--------------------------------------------------------------------------------

`ko5hian` はContextではなくViewを受け取ることができます。
```kotlin
val linearLayout = LinearLayout(context)

ko5hian(linearLayout) {
   linearLayout {
   }
}
```

この場合、Ko5hianは子Viewの「スキャン」を行います。
```kotlin
val linearLayout = ko5hian(context) {
   //                      ^~~~~~~ これは通常のKo5hianです。
   linearLayout {
      textView {}
      textView {}
      imageView {}
   }
}

ko5hian(linearLayout) {
   //   ^~~~~~~~~~~~ これはスキャンを行うKo5hianです。
   linearLayout {
      textView {}
      textView {}
      imageView {}
   }
}
```

2番目の `ko5hian` はViewを生成せず、代わりに既に存在するViewを探し、
それを使います。

![](http://2wiqua.wcaokaze.com/gitbucket/wcaokaze/Ko5hian/raw/master/imgs/scanning.svg)

ですので以下のふたつのスニペットは同等の結果になります。
```kotlin
val linearLayout = ko5hian(context) {
   linearLayout {
      textView {
         view.text = "hello"
      }
   }
}

ko5hian(linearLayout) {
   linearLayout {
      textView {
         view.textColor = 0xffffff opacity 80
      }
   }
}
```
```kotlin
val linearLayout = ko5hian(context) {
   linearLayout {
      textView {
         view.text = "hello"
         view.textColor = 0xffffff opacity 80
      }
   }
}
```

指定されたViewが見つからないときは、Ko5hianは新しいViewを生成して挿入します。

![](http://2wiqua.wcaokaze.com/gitbucket/wcaokaze/Ko5hian/raw/master/imgs/scanning_not_found.svg)


### これがどう便利なのか？

Viewが邪魔なパラメータを大量に持つことがあります。
```kotlin
ko5hian(context) {
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
         view.text = user.name // これが一番メインなのに周りのゴミに埋もれてしまっている！！！
      }

      imageView {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.image = drawable(R.drawable.ic_protected)
         view.visibility = if (user.isProtected) { VISIBLE } else { GONE }
      }
   }
}
```

スキャンを活用しましょう。
```kotlin
// Viewを生成する
val view = ko5hian(context) {
   linearLayout {
      textView {
         view.text = user.name
      }

      imageView {
         view.visibility = if (user.isProtected) { VISIBLE } else { GONE }
      }
   }
}

// Viewのパラメータをセットする
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


### RecyclerView

```kotlin
class UserViewHolder(context: Context)
      : RecyclerView.ViewHolder(LinearLayout(context))
{
   private val usernameView: TextView
   private val protectedIconView: ImageView

   init {
      ko5hian(itemView) {
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
            }

            protectedIconView = imageView {
               layout.width  = WRAP_CONTENT
               layout.height = WRAP_CONTENT
               view.image = drawable(R.drawable.ic_protected)
            }
         }
      }
   }
}
```


### 名前付きView

Viewに名前をつけることができます。
```kotlin
val view = ko5hian(context) {
   linearLayout {
      textView {
         name = "username"
         view.text = user.name
      }

      imageView {
         name = "protected-icon"
         view.visibility = if (user.isProtected) { VISIBLE } else { GONE }
      }
   }
}

ko5hian(view) {
   linearLayout {
      layout.width  = MATCH_PARENT
      layout.height = WRAP_CONTENT
      view.orientation = HORIZONTAL

      textView("username") {
         layout.width  = 0
         layout.height = WRAP_CONTENT
         layout.weight = 1.0f
         layout.marginStart = 16.dip
         view.textColor = 0x313131.opaque
         view.textSizeSp = 16
         view.maxLines = 1
         view.ellipsize = TRUNCATE_AT_END
      }

      imageView("protected-icon") {
         layout.width  = WRAP_CONTENT
         layout.height = WRAP_CONTENT
         view.image = drawable(R.drawable.ic_protected)
      }
   }
}
```

名前は一意でなくても構いません。
```kotlin
val view = ko5hian(context) {
   linearLayout {
      textView {
         name = "menu-item"
         view.text = "followees"
      }

      textView {
         name = "menu-item"
         view.text = "followers"
      }

      textView {
         name = "menu-item"
         view.text = "likes"
      }
   }
}

ko5hian(view) {
   linearLayout {
      layout.width  = MATCH_PARENT
      layout.height = WRAP_CONTENT
      view.orientation = VERTICAL

      // "menu-item"という名前のViewすべてに適用
      textView("menu-item") {
         layout.width  = MATCH_PARENT
         layout.height = WRAP_CONTENT
         view.textColor = 0x313131.opaque
         view.textSizeSp = 16
      }
   }
}
```


### スキャンのスキップ

名前付きViewと名前なしのViewを混在させるととても複雑になります。
```kotlin
val view = ko5hian(context) {
   linearLayout {
      textView {
         name = "named"
      }

      view {
      }

      textView {
         name = "named"
      }

      view {
      }
   }
}

ko5hian(view) {
   linearLayout {
      textView("named") {
      }

      view {
         // ここではどちらのViewが使われるべきでしょう？
      }
   }
}
```

`skipScanningTo(String)` を使ってください。
```kotlin
val view = ko5hian(context) {
   linearLayout {
      textView {
         name = "menu-item"
      }

      view {
      }

      textView {
         name = "menu-item"
      }

      view {
         name = "skip-target"
      }
   }
}

ko5hian(view) {
   linearLayout {
      textView("menu-item") {
      }

      skipScanningTo("skip-target")

      view {
         // ここでは"skip-target"のViewが使われます。
      }
   }
}
```

もしくは、 `skipScanningAll()` も使えます。
```kotlin
val view = ko5hian(context) {
   linearLayout {
      textView {
         name = "menu-item"
      }

      view {
      }

      textView {
         name = "menu-item"
      }

      view {
      }
   }
}

ko5hian(view) {
   linearLayout {
      skipScanningAll()

      view {
         // Viewは見つからなくなります。Ko5hianは新しいViewを生成します。
      }
   }
}
```


非標準のViewをKo5hianで使うための関数
--------------------------------------------------------------------------------

Ko5hianは強力なDSLを実現するために内部関数が醜くなっています。
```kotlin
import android.view.ViewGroup
import android.view.ViewManager

inline fun <P : ViewManager, L> Ko5hian<P, *, L>.yourViewName(
      ko5hianAction: Ko5hianAction<YourViewName, L>
): YourViewName {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::YourViewName,
         ko5hianAction
   )
}

inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.yourViewName(
      withName: String,
      ko5hianAction: Ko5hianAction<YourViewName, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
```

ViewGroupには以下のコードを利用してください。
```kotlin
import android.view.ViewGroup
import android.view.ViewManager

inline fun <P : ViewManager, L> Ko5hian<P, *, L>.yourViewGroupName(
      ko5hianAction: Ko5hianParentAction<YourViewGroupName, L, YourViewGroupName.LayoutParams>
): YourViewGroupName {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::YourViewGroupName,
         { YourViewGroupName.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}

inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.yourViewGroupName(
      withName: String,
      ko5hianAction: Ko5hianParentAction<YourViewGroupName, L, YourViewGroupName.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         { YourViewGroupName.LayoutParams(WRAP_CONTENT, WRAP_CONTENT) },
         ko5hianAction
   )
}
```


インストール
--------------------------------------------------------------------------------

Gradle
```groovy
repositories {
   jcenter()
}

dependencies {
   implementation 'com.wcaokaze:ko5hian:3.0.0-RC2'
}
```

Gradle (Kotlin)
```kotlin
repositories {
   jcenter()
}

dependencies {
   implementation("com.wcaokaze:ko5hian:3.0.0-RC2")
}
```


ライセンス
--------------------------------------------------------------------------------

[DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE](LICENSE)

