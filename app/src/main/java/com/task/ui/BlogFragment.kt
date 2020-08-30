package com.task.ui

import android.R.attr.country
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.models.Result
import com.task.ui.state.BlogStateEvent
import com.task.utils.DataState
import com.task.utils.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class BlogFragment constructor(private val networkHelper: NetworkHelper) :
    Fragment(R.layout.fragment_blog), BlogListAdapter.Interaction , AdapterView.OnItemSelectedListener {

    private val TAG = "MainActivity"
    private val viewModel: BlogsViewModel by viewModels()
    private lateinit var blogListAdapter: BlogListAdapter
    private lateinit var mContext:Context


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSpinner()
        initSearch()
        subscribeObserver()
        checkData()
        swipe_refresh.setOnRefreshListener {
            // activity?.getWindow()?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            checkData()

        }
    }

    private fun initSearch() {
        editTextTextPersonName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var search = p0.toString()
                viewModel.stateEvent(BlogStateEvent.SearchTrackEvent(search))
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun initSpinner() {
        val spinner: Spinner = spinner
        ArrayAdapter.createFromResource(
            mContext,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }

    private fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    private fun checkData() {
        if (!networkHelper.isNetworkConnected()) {
            view?.snack(getString(R.string.no_internet))
            swipe_refresh.isRefreshing = false
        }
        viewModel.stateEvent(BlogStateEvent.GetBlogsEvent())
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Sucess<List<Result>> -> {
                    displayProgressBar(false)
                    Log.e(TAG, "" + dataState.data)
                    swipe_refresh.isRefreshing = false
                    if (dataState.data != null) {
                        blogListAdapter.submitList(
                            blogList = dataState.data,
                            isQueryExhausted = true
                        )
                    }
                }
                is DataState.Error -> {
                    displayError(dataState.exception.message.toString())
                    displayProgressBar(false)
                    swipe_refresh.isRefreshing = false
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    swipe_refresh.isRefreshing = false
                }
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            blogListAdapter = BlogListAdapter(this@BlogFragment)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastPosition == blogListAdapter.itemCount.minus(1)) {
                        Log.d(TAG, "Fragment: attempting to load next page...")
                    }
                }
            })
            adapter = blogListAdapter
        }
    }

    private fun displayError(message: String) {
        if (null != message) {
            view?.snack(message)
        } else {
            view?.snack("Unknown Error")
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onItemSelected(position: Int, item: Result) {

    }

    override fun onItemChecked(isChecked: Boolean) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        viewModel.stateEvent(BlogStateEvent.SelectTrack(p2))
    }

}