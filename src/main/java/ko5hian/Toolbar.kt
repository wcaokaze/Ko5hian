package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Toolbar
import androidx.annotation.RequiresApi

import kotlin.contracts.*

@RequiresApi(21)
@JvmField
val toolbarLayoutParamsInstantiator =
      fun () = Toolbar.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

@RequiresApi(21)
@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.toolbar(
      ko5hianAction: Ko5hianParentAction<Toolbar, L, Toolbar.LayoutParams>
): Toolbar {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::Toolbar,
         toolbarLayoutParamsInstantiator,
         ko5hianAction
   )
}

@RequiresApi(21)
@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.toolbar(
      withName: String,
      ko5hianAction: Ko5hianParentAction<Toolbar, L, Toolbar.LayoutParams>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         toolbarLayoutParamsInstantiator,
         ko5hianAction
   )
}

var Toolbar.titleTextColor: Int
   @Deprecated(message = "The getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   @RequiresApi(21)
   inline set(value) = setTitleTextColor(value)
