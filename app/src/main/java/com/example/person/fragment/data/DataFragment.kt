package com.example.person.fragment.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.person.R
import com.example.person.fragment.data.adapter.DataAdapter
import com.example.person.fragment.data.viewmodel.DataViewModel
import com.example.person.fragment.post.PostFragment
import kotlinx.android.synthetic.main.fragment_data.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataFragment : Fragment() {
    private lateinit var dataAdapter: DataAdapter
    private val dataViewModel: DataViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_data, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentView()
        initViewModel()
        initViewClick()
        dataViewModel.getDataList()
    }

    private fun initViewClick() {
        vErrorLoadData.setOnClickListener {
            dataViewModel.getDataList()
            vErrorLoadData.visibility = View.GONE
            vLoadingData.visibility = View.VISIBLE
        }
    }

    private fun initFragmentView() {
        dataAdapter = DataAdapter(mutableListOf()) {
            val specialtyFragment = PostFragment.newInstance(it)
            val fragment = activity?.supportFragmentManager?.beginTransaction()
            fragment?.replace(R.id.rootFragment, specialtyFragment)
            fragment?.addToBackStack(null)
            fragment?.commit()
        }
        rvData.adapter = dataAdapter
    }

    private fun initViewModel() {
        dataViewModel.getUserDataList.observe(viewLifecycleOwner, Observer {
            dataAdapter.updateList(it)
        })
        dataViewModel.loadingData.observe(viewLifecycleOwner, Observer {
            vLoadingData.visibility = View.GONE
        })
        dataViewModel.errorNullData.observe(viewLifecycleOwner, Observer {
            context?.run {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
            vErrorLoadData.visibility = View.VISIBLE
        })
    }
}