package com.example.person.fragment.post

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.entity.localmodels.UserPost
import com.example.person.R
import com.example.simplemovieapp.fragments.moviedetail.adapter.UserPostAdapter
import com.example.person.fragment.post.viewmodel.UserPostViewModel
import kotlinx.android.synthetic.main.fragment_data.*
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.fragment_post.vErrorLoadData
import kotlinx.android.synthetic.main.fragment_post.vLoadingData

import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : Fragment() {

    private val userPostViewModel: UserPostViewModel by viewModel()
    private var moveId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            moveId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    private fun initData() {
        userPostViewModel.getPostData(moveId)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViewClick()
    }

    private fun initViewModel() {

        userPostViewModel.getTopCastMoveData.observe(
            viewLifecycleOwner,
            Observer(::initTopCastDataView)
        )
        userPostViewModel.errorNullData.observe(viewLifecycleOwner, {
            context?.run {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
            vErrorLoadData.visibility = View.VISIBLE
        })
        userPostViewModel.loadingData.observe(viewLifecycleOwner, { isVisible ->
            vLoadingData.visibility = View.GONE
        })

        userPostViewModel.deletePostItem.observe(viewLifecycleOwner, { postId ->
            context?.run {
                Toast.makeText(context, getString(R.string.you_delete) + postId, Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }

    private fun initViewClick() {
        vErrorLoadData.setOnClickListener {
            initData()
            vErrorLoadData.visibility = View.GONE
            vLoadingData.visibility = View.VISIBLE
        }
    }

    private fun initTopCastDataView(posts: List<UserPost>) {
        rvPost.setHasFixedSize(true)
        rvPost.adapter = UserPostAdapter(posts) { postId ->
            userPostViewModel.deletePostData(postId)
        }
    }

    companion object {
        private const val ARG_PARAM1 = "userId"

        @JvmStatic
        fun newInstance(param1: Int) =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}




