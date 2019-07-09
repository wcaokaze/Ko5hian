package com.wcaokaze.ko5hian

val androidViews = listOf(
      ViewConfiguration("android.view.View"),
      ViewConfiguration("android.widget.Button"),
      ViewConfiguration("android.widget.CalendarView"),
      ViewConfiguration("android.widget.CheckBox"),
      ViewConfiguration("android.widget.CheckedTextView"),
      ViewConfiguration("android.widget.Chronometer"),
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
      ViewConfiguration("android.widget.ProgressBar"),
      ViewConfiguration("android.widget.QuickContactBadge"),
      ViewConfiguration("android.widget.RadioButton"),
      ViewConfiguration("android.widget.RadioGroup"),
      ViewConfiguration("android.widget.RatingBar"),
      ViewConfiguration("android.widget.RelativeLayout"),
      ViewConfiguration("android.widget.SeekBar"),
      ViewConfiguration("android.widget.Space"),
      ViewConfiguration("android.widget.Switch"),
      ViewConfiguration("android.widget.TabHost"),
      ViewConfiguration("android.widget.TableLayout"),
      ViewConfiguration("android.widget.TableRow"),
      ViewConfiguration("android.widget.TabWidget"),
      ViewConfiguration("android.widget.TextSwitcher"),
      ViewConfiguration("android.widget.TextView"),
      ViewConfiguration("android.widget.TimePicker"),
      ViewConfiguration("android.widget.ToggleButton"),
      ViewConfiguration("android.widget.Toolbar"),
      ViewConfiguration("android.widget.VideoView"),
      ViewConfiguration("android.widget.ViewFlipper"),
      ViewConfiguration("android.widget.ViewSwitcher"),
      ViewConfiguration("android.webkit.WebView")
)

val androidViewGroups = listOf(
      ViewGroupConfiguration("android.widget.FrameLayout"),
      ViewGroupConfiguration("android.widget.HorizontalScrollView",
            lParamsClassName = "android.widget.FrameLayout.LayoutParams"),
      ViewGroupConfiguration("android.widget.ImageSwitcher",
            lParamsClassName = "android.widget.FrameLayout.LayoutParams"),
      ViewGroupConfiguration("android.widget.LinearLayout"),
      ViewGroupConfiguration("android.widget.RadioGroup"),
      ViewGroupConfiguration("android.widget.RelativeLayout"),
      ViewGroupConfiguration("android.widget.TabHost",
            lParamsClassName = "android.widget.FrameLayout.LayoutParams"),
      ViewGroupConfiguration("android.widget.TableLayout"),
      ViewGroupConfiguration("android.widget.TableRow"),
      ViewGroupConfiguration("android.widget.TabWidget",
            lParamsClassName = "android.widget.LinearLayout.LayoutParams"),
      ViewGroupConfiguration("android.widget.TextSwitcher",
            lParamsClassName = "android.widget.FrameLayout.LayoutParams"),
      ViewGroupConfiguration("android.widget.Toolbar"),
      ViewGroupConfiguration("android.widget.ViewFlipper",
            lParamsClassName = "android.widget.FrameLayout.LayoutParams"),
      ViewGroupConfiguration("android.widget.ViewSwitcher",
            lParamsClassName = "android.widget.FrameLayout.LayoutParams")
)
