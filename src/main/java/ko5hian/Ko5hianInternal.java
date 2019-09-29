package ko5hian;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wcaokaze.ko5hian.R;

import java.util.Iterator;
import java.util.NoSuchElementException;

import kotlin.jvm.functions.Function0;

public final class Ko5hianInternal {
   private static float mDisplayDensity = 0.0f;

   static void setDisplayDensity(final float displayDensity) {
      mDisplayDensity = displayDensity;
   }

   public static void addView(final Object viewManager, final View view) {
      if (viewManager instanceof ViewGroup) {
         final ViewGroup parent = (ViewGroup) viewManager;
         final ViewGroup.LayoutParams layoutParams = createLayoutParams(parent);

         parent.addView(view, layoutParams);
      } else if (viewManager instanceof Ko5hianRoot) {
         final Ko5hianRoot parent = (Ko5hianRoot) viewManager;
         final ViewGroup.LayoutParams layoutParams = parent.createLayoutParams();

         parent.addView(view, layoutParams);
      } else {
         throw new IllegalStateException();
      }
   }

   public static void setLayoutParams(final Object parentViewManager, final View view) {
      if (view.getLayoutParams() != null) { return; }

      final ViewGroup.LayoutParams layoutParams = createLayoutParams(parentViewManager);
      view.setLayoutParams(layoutParams);
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
         return createLayoutParams((ViewGroup) viewManager);
      } else if (viewManager instanceof Ko5hianRoot) {
         final Ko5hianRoot parent = (Ko5hianRoot) viewManager;
         return parent.createLayoutParams();
      } else {
         throw new IllegalStateException();
      }
   }

   public static ViewGroup.LayoutParams createLayoutParams(final ViewGroup parent) {
      final Function0<?> layoutParamsInstantiator =
            (Function0<?>) parent.getTag(R.id.view_tag_layout_params_instantiator);

      return (ViewGroup.LayoutParams) layoutParamsInstantiator.invoke();
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

   public static <V extends View> V findView(final Object viewManager,
                                             final Class<V> viewClass)
   {
      if (viewManager instanceof ViewGroup) {
         return findView((ViewGroup) viewManager, viewClass);
      } else if (viewManager instanceof Ko5hianRoot) {
         final Ko5hianRoot parent = (Ko5hianRoot) viewManager;
         return parent.findView(viewClass);
      } else {
         throw new IllegalStateException();
      }
   }

   public static <V extends View> V findView(final ViewGroup parent,
                                             final Class<V> viewClass)
   {
      int scannedIndex = (int) parent.getTag(R.id.view_tag_scanned_index);

      if (scannedIndex == -1) { return null; }

      for (; scannedIndex < parent.getChildCount(); scannedIndex++) {
         final View child = parent.getChildAt(scannedIndex);

         if (child.getClass().equals(viewClass)) {
            parent.setTag(R.id.view_tag_scanned_index, scannedIndex + 1);

            @SuppressWarnings("unchecked")
            final V casted = (V) child;

            return casted;
         }
      }

      parent.setTag(R.id.view_tag_scanned_index, -1);

      return null;
   }

   public static Iterator<View> findChildrenByName(final ViewGroup parent,
                                                   final String name)
   {
      if (parent.getChildCount() == 0) {
         return new EmptyIterator();
      }

      return new ViewNameFilterIterator(parent, name);
   }

   private static final class EmptyIterator implements Iterator<View> {
      @Override
      public boolean hasNext() {
         return false;
      }

      @Override
      public View next() {
         throw new NoSuchElementException();
      }
   }

   private static final class ViewNameFilterIterator implements Iterator<View> {
      private final ViewGroup mParent;
      private final String mName;

      private View mNextView;
      private int mNextViewIndex;

      ViewNameFilterIterator(final ViewGroup parent, final String name) {
         mParent = parent;
         mName = name;

         for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            final String childName = (String) child.getTag(R.id.view_tag_name);

            if (childName == null) { continue; }
            if (childName.equals(name)) { continue; }

            mNextView = child;
            mNextViewIndex = i;

            return;
         }

         throw new NoSuchElementException(
               "child View with name '" + name + "' not found");
      }

      @Override
      public boolean hasNext() {
         return mNextView != null;
      }

      @Override
      public View next() {
         final ViewGroup parent = mParent;
         final String name = mName;

         final View next = mNextView;

         for (int i = mNextViewIndex + 1; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            final String childName = (String) child.getTag(R.id.view_tag_name);

            if (childName == null) { continue; }
            if (childName.equals(name)) { continue; }

            mNextView = child;
            mNextViewIndex = i;

            return next;
         }

         mNextView = null;
         mNextViewIndex = parent.getChildCount();

         return next;
      }
   };
}
