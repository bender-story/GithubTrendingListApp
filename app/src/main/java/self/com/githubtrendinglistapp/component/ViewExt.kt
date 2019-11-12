package self.com.githubtrendinglistapp.component

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("makeVisible")
fun View.makeVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
@BindingAdapter("visibleOnText")
fun AppCompatTextView.visibleOnText(visible: String?) {
    this.visibility = if (visible.isNullOrEmpty()) View.GONE else View.VISIBLE
}