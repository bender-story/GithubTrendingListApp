package self.com.githubtrendinglistapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import self.com.githubtrendinglistapp.viewmodel.MainRowViewModel
import self.com.githubtrendinglistapp.databinding.ViewMainRowItemBinding

// Main List adapter
class MainAdapter(val items: ArrayList<MainRowViewModel>?) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewMainRowItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position)!!)

    inner class ViewHolder(val binding: ViewMainRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainRowViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }
    }
}
