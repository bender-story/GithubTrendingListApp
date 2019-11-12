package self.com.githubtrendinglistapp.component


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

// ImageURL databinding for AppCompatImageView
@BindingAdapter("getImage")
fun AppCompatImageView.getImage(path: String?) {
    Glide.with(this.context).load(path).apply(RequestOptions.circleCropTransform()).into(this)
}