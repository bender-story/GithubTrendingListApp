package self.com.githubtrendinglistapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import self.com.githubtrendinglistapp.R
import self.com.githubtrendinglistapp.viewmodel.MainRowViewModel
import self.com.githubtrendinglistapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    var viewModel: MainViewModel? = null
    private var rowViewModels: ArrayList<MainRowViewModel>? = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeList()
        fetchData()
    }

    private fun observeList() {
        viewModel?.trendingList?.observe(this,
            Observer {
                initRecyclerView()
            })
    }

    private fun initRecyclerView() {
        doAsync {

                rowViewModels= viewModel?.getMainRowViewModel() as ArrayList<MainRowViewModel>?
            // Add list to adapter using UI Thread
        uiThread {
                mainRecyclerView.adapter = MainAdapter(rowViewModels)
                mainRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

        }

    }

    private fun fetchData(){
        viewModel?.showLoader?.set(true)
        viewModel?.fetchTrendingList({},{})
    }
}
