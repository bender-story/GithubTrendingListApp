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
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import self.com.githubtrendinglistapp.component.makeVisible
import self.com.githubtrendinglistapp.viewmodel.ViewState
/**
 * main Activity is the view which loads all the data on the Ui
 *
 */

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private var rowViewModels: ArrayList<MainRowViewModel>? = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        supportActionBar?.hide()
        observeList()
        callRetry()
        fetchData()
        pullToRefresh()
    }
    /**
     * observer to check live data changes
     */
    private fun observeList() {
        viewModel?.trendingList?.observe(this,
            Observer {
                initRecyclerView()
            })
    }
    /**
     * load recyclerview with latest changes
     */
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
    /**
     * fetch data from the server
     * @param refresh true when pull to refresh is called
     */
    private fun fetchData(refresh:Boolean=false){
        if(!refresh)changeState(ViewState.LOADER)
        if(!NetworkUtils.isNetworkAvailable(this))
            changeState(ViewState.ERROR)
        else
            fetchTrendingList()
    }
    /**
     * fetch data from the server
     */
    private fun fetchTrendingList(){
        viewModel?.fetchTrendingList({
        },{
            changeUI(false,ViewState.ERROR)
        })
    }
    /**
     * change the UI state
     * @param showRefresh show pull to refresh
     * @param viewState set the viewstate
     */
    private fun changeUI(showRefresh: Boolean,viewState: ViewState){
            showRefesh(showRefresh)
            changeState(viewState)
    }

    /**
     * init call retry button action listener
     */

    private fun callRetry(){
        retryButton.setOnClickListener {
            fetchData()
        }

    }

    /**
     * init pull to refresh action listener
     */
    private fun pullToRefresh(){
        refresh.setOnRefreshListener{
            showRefesh(true)
            fetchData(true)

        }
    }
    /**
     * show pull to refresh loader
     * @param show true show the loader else hide
     */
    private fun showRefesh(show:Boolean){
        refresh.isRefreshing=show
    }

    /**
     * change the state of the view
     * ViewState.LOADER -> show loader
     * ViewState.ERROR -> show error
     * ViewState.SHOW_LIST -> show list
     * @param viewState send view state
     */
    private fun changeState(viewState:ViewState=ViewState.LOADER){
        when(viewState){
            ViewState.LOADER->changeState(true,false,false)
            ViewState.ERROR->changeState(false,true,false)
            ViewState.SHOW_LIST->changeState(false,false,true)

        }
    }

    /**
     * change the state of the view
     * @param loader  -> show loader
     * @param error  -> show error
     * @param recyclerView  -> show list
     */
    private fun changeState(loader:Boolean,error: Boolean,recyclerView: Boolean){
        loaderView.makeVisible(loader)
        errorView.makeVisible(error)
        refresh.makeVisible(recyclerView)
    }
}
