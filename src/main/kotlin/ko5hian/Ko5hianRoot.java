package ko5hian;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;

public final class Ko5hianRoot implements ViewManager {
   public final Context context;

   public Ko5hianRoot(final Context context) {
      this.context = context;
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
