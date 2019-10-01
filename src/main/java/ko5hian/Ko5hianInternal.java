package ko5hian;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;

import com.wcaokaze.ko5hian.R;

import java.util.Iterator;
import java.util.NoSuchElementException;

import kotlin.jvm.functions.Function0;

public final class Ko5hianInternal {
   static float displayDensity = 0.0f;

   public static Context context = null;
   public static Function0<?> layoutParamsInstantiator = null;
   public static int scannedIndex = 0;

   public static void addView(final Object viewManager, final View view) {
      final ViewManager parent = (ViewManager) viewManager;

      final ViewGroup.LayoutParams layoutParams =
            (ViewGroup.LayoutParams) layoutParamsInstantiator.invoke();

      parent.addView(view, layoutParams);
   }

   public static void setLayoutParams(final View view) {
      if (view.getLayoutParams() != null) { return; }

      final ViewGroup.LayoutParams layoutParams =
            (ViewGroup.LayoutParams) layoutParamsInstantiator.invoke();

      view.setLayoutParams(layoutParams);
   }

   public static int dipToPx(final int dip) {
      final int px = (int) (dip * displayDensity);

      if (px != 0) {
         return px;
      } else if (dip < 0) {
         return -1;
      } else {
         return 1;
      }
   }

   public static int dipToPx(final double dip) {
      final int px = (int) (dip * displayDensity);

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
      int scannedIndex = Ko5hianInternal.scannedIndex;

      if (scannedIndex == -1) { return null; }

      for (; scannedIndex < parent.getChildCount(); scannedIndex++) {
         final View child = parent.getChildAt(scannedIndex);

         if (child.getClass().equals(viewClass)) {
            Ko5hianInternal.scannedIndex = scannedIndex + 1;

            @SuppressWarnings("unchecked")
            final V casted = (V) child;

            return casted;
         }
      }

      Ko5hianInternal.scannedIndex = -1;

      return null;
   }

   public static Iterator<View> findChildrenByName(final ViewGroup parent,
                                                   final String name)
   {
      return new ViewNameFilterIterator(parent, name);
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
