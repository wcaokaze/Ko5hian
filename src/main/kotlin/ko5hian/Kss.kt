package ko5hian

import android.view.View
import android.view.ViewGroup

class Kss<V : View, L : ViewGroup.LayoutParams>(
      val name: String,
      val styleApplier: Ko5hianViewBuilder<V, L>.() -> Unit,
      val childStyles: Map<String, Kss<*, *>>,
      val anonymousChildStyles: List<Kss<*, *>>
)
