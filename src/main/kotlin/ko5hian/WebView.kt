package ko5hian

import android.view.ViewManager
import android.webkit.WebView

import kotlin.contracts.*

@ExperimentalContracts
fun <P : ViewManager, L> Ko5hian<P, *, L>.webView(
      ko5hianAction: Ko5hianAction<WebView, L>
): WebView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::WebView,
         ko5hianAction
   )
}
