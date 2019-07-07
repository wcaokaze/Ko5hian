package com.wcaokaze.ko5hian

val androidViews = listOf(
      ViewConfiguration("android.view.View"),
      ViewConfiguration("android.widget.Button"),
      ViewConfiguration("android.widget.CalendarView"),
      ViewConfiguration("android.widget.CheckBox"),
      ViewConfiguration("android.widget.CheckedTextView"),
      ViewConfiguration("android.widget.Chronometer"),
      ViewConfiguration("android.widget.CompoundButton"),
      ViewConfiguration("android.widget.DatePicker"),
      ViewConfiguration("android.widget.EditText"),
      ViewConfiguration("android.widget.FrameLayout"),
      ViewConfiguration("android.widget.HorizontalScrollView"),
      ViewConfiguration("android.widget.ImageButton"),
      ViewConfiguration("android.widget.ImageSwitcher"),
      ViewConfiguration("android.widget.ImageView"),
      ViewConfiguration("android.widget.LinearLayout"),
      ViewConfiguration("android.widget.MediaController"),
      ViewConfiguration("android.widget.MultiAutoCompleteTextView"),
      ViewConfiguration("android.widget.NumberPicker"),
      ViewConfiguration("android.widget.OverScroller"),
      ViewConfiguration("android.widget.ProgressBar"),
      ViewConfiguration("android.widget.QuickContactBadge"),
      ViewConfiguration("android.widget.RadioButton"),
      ViewConfiguration("android.widget.RadioGroup"),
      ViewConfiguration("android.widget.RatingBar"),
      ViewConfiguration("android.webkit.WebView")
)

val androidViewGroups = listOf(
      ViewGroupConfiguration("android.widget.FrameLayout"),
      ViewGroupConfiguration("android.widget.HorizontalScrollView",
            lParamsClassName = "android.widget.FrameLayout.LayoutParams"),
      ViewGroupConfiguration("android.widget.ImageSwitcher",
            lParamsClassName = "android.widget.FrameLayout.LayoutParams"),
      ViewGroupConfiguration("android.widget.LinearLayout"),
      ViewGroupConfiguration("android.widget.RadioGroup")
)
