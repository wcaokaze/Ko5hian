package ko5hian;

import android.content.Context;
import android.view.ViewGroup;

import kotlin.jvm.functions.Function0;

public final class Ko5hianInternal {
   public static Context getContext(final Object viewManager) {
      if (viewManager instanceof ViewGroup) {
         final ViewGroup parent = (ViewGroup) viewManager;
         return parent.getContext();
      } else if (viewManager instanceof Ko5hianRoot) {
         final Ko5hianRoot parent = (Ko5hianRoot) viewManager;
         return parent.context;
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
         return new ViewGroup.LayoutParams(
               ViewGroup.LayoutParams.WRAP_CONTENT,
               ViewGroup.LayoutParams.WRAP_CONTENT);
      } else {
         throw new IllegalStateException();
      }
   }
}
