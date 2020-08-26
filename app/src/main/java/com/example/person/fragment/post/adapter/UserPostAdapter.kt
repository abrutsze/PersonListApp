package com.example.simplemovieapp.fragments.moviedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.entity.localmodels.UserPost
import com.example.person.R
import kotlinx.android.synthetic.main.item_user_post.view.*

class UserPostAdapter(
    private var itemList: List<UserPost>,
    var deletePost: (postId: Int) -> Unit
) :
    RecyclerView.Adapter<UserPostAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: UserPost) {
            with(itemView) {
                vPostName.text = item.title?.run { this }
                vPostBody.text = item.body?.run { this }
                vDeletePost.setOnClickListener {
                    item.id?.apply(deletePost)
                }
            }

        }
    }

}
