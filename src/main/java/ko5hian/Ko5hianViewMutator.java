package ko5hian;

import android.view.View;

public class Ko5hianViewMutator extends Ko5hianRoot {
   private final View mView;

   public Ko5hianViewMutator(final View view) {
      super(view.getContext());

      mView = view;
   }

   @Override
   @SuppressWarnings("unchecked")
   public <V extends View> V findView(final Class<V> viewClass) {
      return (V) mView;
   }
}
