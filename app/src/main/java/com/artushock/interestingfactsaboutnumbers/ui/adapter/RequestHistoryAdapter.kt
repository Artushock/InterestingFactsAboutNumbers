package com.artushock.interestingfactsaboutnumbers.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.artushock.interestingfactsaboutnumbers.R
import com.artushock.interestingfactsaboutnumbers.model.NumberRequestItem

class RequestHistoryAdapter(
    private var itemSet: List<NumberRequestItem>,
) : RecyclerView.Adapter<RequestHistoryAdapter.ViewHolder>() {

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: NumberRequestItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemContainer: View = view.findViewById(R.id.request_item_container)
        private val numberTextView: TextView = view.findViewById(R.id.request_item_number)
        private val factTextView: TextView = view.findViewById(R.id.request_item_fact)

        fun bind(item: NumberRequestItem) {
            numberTextView.text = item.number
            factTextView.text = item.fact
            itemContainer.setOnClickListener {
                mListener?.onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.request_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemSet[position])
    }

    override fun getItemCount(): Int = itemSet.size
}