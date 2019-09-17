package ko5hian

import android.view.ViewGroup
import android.webkit.WebView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.webView(
            builderAction: Ko5hianViewBuilder<WebView, L>.() -> Unit
      )
      = addView(::WebView, builderAction)
