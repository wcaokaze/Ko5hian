package ko5hian

import android.view.ViewGroup
import android.webkit.WebView

inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.webView(
            target: WebView? = null,
            builderAction: Ko5hianViewBuilder<WebView, L>.() -> Unit
      )
      = addView(target, ::WebView, builderAction)
