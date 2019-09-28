package ko5hian;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;

public class Ko5hianRoot implements ViewManager {
   private final Context mContext;

   public Ko5hianRoot(final Context context) {
      mContext = context;

      Ko5hianInternal.setDisplayDensity(
            context.getResources().getDisplayMetrics().density);
   }

   public Context getContext() {
      return mContext;
   }

   public ViewGroup.LayoutParams createLayoutParams() {
      return new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
   }

   public <V extends View> V findView(final Class<V> viewClass) {
      return null;
   }

   @Override
   public void addView(View view, ViewGroup.LayoutParams params) {
      // nop
   }

   @Override
   public void removeView(View view) {
      // nop
   }

   @Override
   public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
      // nop
   }
}
