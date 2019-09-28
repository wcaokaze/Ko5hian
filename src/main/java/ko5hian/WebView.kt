package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.webkit.WebView

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.webView(
      ko5hianAction: Ko5hianAction<WebView, L>
): WebView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::WebView,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.webView(
      withName: String,
      ko5hianAction: Ko5hianAction<WebView, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}
