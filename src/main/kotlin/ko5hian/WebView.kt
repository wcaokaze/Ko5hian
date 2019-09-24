package ko5hian

import android.view.ViewGroup
import android.webkit.WebView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.webView(
            style: String? = null,
            reuse: WebView = WebView(context),
            builderAction: Ko5hianViewBuilder<WebView, L>.() -> Unit
      ): WebView
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(style, "webView", reuse, builderAction)
}
