package com.example.person.fragment.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.entity.localmodels.User
import com.example.person.R
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter(
    private var itemList: List<User>,
    var data: (userId: Int) -> Unit,
) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateList(list: List<User>?) {
        list?.let {
            itemList = it
            notifyDataSetChanged()
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: User) {
            with(itemView) {
                vId.text = item.id.run { context.getString(R.string.id) + this }
                vName.text = item.name?.run { context.getString(R.string.name) + this }
                vUsername.text = item.username?.run { context.getString(R.string.username) + this }
                vStreet.text =
                    item.address?.street?.run { context.getString(R.string.street) + this }
                vCity.text = item.address?.city?.run { context.getString(R.string.city) + this }
            }
            itemView.setOnClickListener {
                item.id?.apply(data)
            }

        }
    }
}