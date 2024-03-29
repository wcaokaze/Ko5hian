package ko5hian;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;

public class Ko5hianRoot implements ViewManager {
   public Ko5hianRoot(final Context context) {
      Ko5hianInternal.displayDensity = context.getResources().getDisplayMetrics().density;
   }

   public <V extends View> V findView(final Class<V> viewClass) {
      return null;
   }

   public void addView(final View view, final int index, final ViewGroup.LayoutParams params) {
      view.setLayoutParams(params);
   }

   @Override
   public void addView(final View view, final ViewGroup.LayoutParams params) {
      view.setLayoutParams(params);
   }

   @Override
   public void removeView(final View view) {
      // nop
   }

   @Override
   public void updateViewLayout(final View view, final ViewGroup.LayoutParams params) {
      // nop
   }
}
