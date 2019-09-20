package ko5hian

import android.view.ViewGroup
import android.webkit.WebView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <L : ViewGroup.LayoutParams>
      Ko5hianViewParent<L>.webView(
            target: WebView? = null,
            builderAction: Ko5hianViewBuilder<WebView, L>.() -> Unit
      ): WebView
{
   contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }

   return addView(target, ::WebView, builderAction)
}
