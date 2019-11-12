package self.com.githubtrendinglistapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import self.com.githubtrendinglistapp.R
import self.com.githubtrendinglistapp.viewmodel.MainRowViewModel
import self.com.githubtrendinglistapp.viewmodel.MainViewModel
import com.android.rahul.movies.utils.NetworkUtils
import kotlinx.android.synthetic.main.view_error_main.*
import self.com.githubtrendinglistapp.component.makeVisible
import self.com.githubtrendinglistapp.viewmodel.ViewState


class MainActivity : AppCompatActivity() {
    var viewModel: MainViewModel? = null
    private var rowViewModels: ArrayList<MainRowViewModel>? = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        supportActionBar?.hide()
        observeList()
        callRetry()
        fetchData()
        pullToRefresh()
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
                changeUI(false,ViewState.SHOW_LIST)
                mainRecyclerView.adapter = MainAdapter(rowViewModels)
                mainRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity) as RecyclerView.LayoutManager?
            }

        }

    }

    private fun fetchData(){
        changeState(ViewState.LOADER)
        if(!NetworkUtils.isNetworkAvailable(this))
            changeState(ViewState.ERROR)
        else
            fetchTrendingList()
    }

    private fun fetchTrendingList(){
        viewModel?.fetchTrendingList({
            setTimer()
        },{
            changeUI(false,ViewState.ERROR)
        })
    }

    private fun changeUI(showRefresh: Boolean,viewState: ViewState){
            showRefesh(showRefresh)
            changeState(viewState)
    }

    private fun setTimer(){
        viewModel?.callServiceEveryTwoHours {
            runOnUiThread {
                this@MainActivity.fetchData()
            }
        }
    }

    private fun callRetry(){
        retryButton.setOnClickListener {
            fetchData()
        }

    }


    private fun pullToRefresh(){
        refresh.setOnRefreshListener{
            showRefesh(true)
            fetchData()

        }
    }

    private fun showRefesh(show:Boolean){
        refresh.isRefreshing=show
    }

    private fun changeState(viewState:ViewState=ViewState.LOADER){
        when(viewState){
            ViewState.LOADER->changeState(true,false,false)
            ViewState.ERROR->changeState(false,true,false)
            ViewState.SHOW_LIST->changeState(false,false,true)

        }
    }
    private fun changeState(loader:Boolean,error: Boolean,recyclerView: Boolean){
        loaderView.makeVisible(loader)
        errorView.makeVisible(error)
        refresh.makeVisible(recyclerView)
    }
}
