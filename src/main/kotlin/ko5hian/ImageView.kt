@file:Suppress("UNUSED")
package ko5hian

import android.widget.ImageView
import android.graphics.Bitmap
import android.graphics.drawable.Drawable

val Ko5hianBuilder<ImageView, *>.SCALE_TYPE_CENTER get() = ImageView.ScaleType.CENTER
val Ko5hianBuilder<ImageView, *>.CENTER_CROP       get() = ImageView.ScaleType.CENTER_CROP
val Ko5hianBuilder<ImageView, *>.CENTER_INSIDE     get() = ImageView.ScaleType.CENTER_INSIDE
val Ko5hianBuilder<ImageView, *>.FIT_CENTER        get() = ImageView.ScaleType.FIT_CENTER
val Ko5hianBuilder<ImageView, *>.FIT_END           get() = ImageView.ScaleType.FIT_END
val Ko5hianBuilder<ImageView, *>.FIT_START         get() = ImageView.ScaleType.FIT_START
val Ko5hianBuilder<ImageView, *>.FIT_XY            get() = ImageView.ScaleType.FIT_XY
val Ko5hianBuilder<ImageView, *>.MATRIX            get() = ImageView.ScaleType.MATRIX

var ImageView.image: Drawable?
   get() = drawable
   set(value) = setImageDrawable(value)

var ImageView.bitmap: Bitmap?
   @Deprecated("This getter always throws an Exception", level = DeprecationLevel.ERROR)
   get() = throw UnsupportedOperationException()
   set(value) = setImageBitmap(value)
