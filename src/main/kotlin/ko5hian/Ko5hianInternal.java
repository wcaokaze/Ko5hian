package ko5hian;

import android.content.Context;
import android.view.ViewGroup;

import kotlin.jvm.functions.Function0;

public final class Ko5hianInternal {
   private static float mDisplayDensity = 0.0f;

   static void setDisplayDensity(final float displayDensity) {
      mDisplayDensity = displayDensity;
   }

   public static Context getContext(final Object viewManager) {
      if (viewManager instanceof ViewGroup) {
         final ViewGroup parent = (ViewGroup) viewManager;
         return parent.getContext();
      } else if (viewManager instanceof Ko5hianRoot) {
         final Ko5hianRoot parent = (Ko5hianRoot) viewManager;
         return parent.getContext();
      } else {
         throw new IllegalStateException();
      }
   }

   public static ViewGroup.LayoutParams createLayoutParams(final Object viewManager) {
      if (viewManager instanceof ViewGroup) {
         final ViewGroup parent = (ViewGroup) viewManager;

         final Function0<?> layoutParamsInstantiator =
               (Function0<?>) parent.getTag(Ko5hianKt.VIEW_TAG_ID_LAYOUT_PARAMS_INSTANTIATOR);

         return (ViewGroup.LayoutParams) layoutParamsInstantiator.invoke();
      } else if (viewManager instanceof Ko5hianRoot) {
         final Ko5hianRoot parent = (Ko5hianRoot) viewManager;
         return parent.createLayoutParams();
      } else {
         throw new IllegalStateException();
      }
   }

   public static int dipToPx(final int dip) {
      final int px = (int) (dip * mDisplayDensity);

      if (px != 0) {
         return px;
      } else if (dip < 0) {
         return -1;
      } else {
         return 1;
      }
   }

   public static int dipToPx(final double dip) {
      final int px = (int) (dip * mDisplayDensity);

      if (px != 0) {
         return px;
      } else if (dip < 0) {
         return -1;
      } else {
         return 1;
      }
   }
}
