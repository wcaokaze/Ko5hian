@file:Suppress("UNUSED")
package ko5hian

import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ImageView
import android.graphics.Bitmap
import android.graphics.drawable.Drawable

import kotlin.contracts.*

@ExperimentalContracts
inline fun <P : ViewManager, L> Ko5hian<P, *, L>.imageView(
      ko5hianAction: Ko5hianAction<ImageView, L>
): ImageView {
   contract { callsInPlace(ko5hianAction, InvocationKind.EXACTLY_ONCE) }

   return addView(
         ::ImageView,
         ko5hianAction
   )
}

@ExperimentalContracts
inline fun <P : ViewGroup, L> Ko5hian<P, *, L>.imageView(
      withName: String,
      ko5hianAction: Ko5hianAction<ImageView, L>
) {
   contract { callsInPlace(ko5hianAction, InvocationKind.AT_LEAST_ONCE) }

   mutateView(
         withName,
         ko5hianAction
   )
}

val Ko5hianView<ImageView>.SCALE_TYPE_CENTER inline get() = ImageView.ScaleType.CENTER
val Ko5hianView<ImageView>.CENTER_CROP       inline get() = ImageView.ScaleType.CENTER_CROP
val Ko5hianView<ImageView>.CENTER_INSIDE     inline get() = ImageView.ScaleType.CENTER_INSIDE
val Ko5hianView<ImageView>.FIT_CENTER        inline get() = ImageView.ScaleType.FIT_CENTER
val Ko5hianView<ImageView>.FIT_END           inline get() = ImageView.ScaleType.FIT_END
val Ko5hianView<ImageView>.FIT_START         inline get() = ImageView.ScaleType.FIT_START
val Ko5hianView<ImageView>.FIT_XY            inline get() = ImageView.ScaleType.FIT_XY
val Ko5hianView<ImageView>.MATRIX            inline get() = ImageView.ScaleType.MATRIX

var ImageView.image: Drawable?
   inline get() = drawable
   inline set(value) = setImageDrawable(value)

var ImageView.bitmap: Bitmap?
   @Deprecated("This getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   inline set(value) = setImageBitmap(value)
