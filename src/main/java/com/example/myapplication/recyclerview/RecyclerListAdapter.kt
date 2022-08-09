package com.example.myapplication.recyclerview

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.myapplication.Presenter
import com.example.myapplication.R
import com.example.myapplication.TimeData
import java.util.*

class RecyclerListAdapter : RecyclerView.Adapter<ItemViewHolder>(), ItemTouchHelperAdapter {
    val list: ArrayList<TimeData?> = ArrayList()
    protected var clickCallback: AdapterClickCallback<TimeData>? = null
    protected var dismissCallback: AdapterDismissCallback? = null
    var chosenTime: Int = 0

    fun attachClickCallback(clickCallback: AdapterClickCallback<TimeData>) {
        this.clickCallback = clickCallback
    }

    fun attachDismissCallback(dismissCallback: AdapterDismissCallback) {
        this.dismissCallback = dismissCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        val itemViewHolder = ItemViewHolder(view)

        itemViewHolder.itemView.setOnClickListener {
            list[itemViewHolder.adapterPosition]?.let { it1 ->
                clickCallback?.onItemClick(it1, itemViewHolder.itemView, itemViewHolder.adapterPosition) }
        }

        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("Bind", "position = $position")
        holder.bind(list[position], chosenTime)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Log.d("move", "from $fromPosition to $toPosition")
        Log.d("move", "chosenTime $chosenTime was")
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
                val j = i + 1
                Log.d("swap", "i = $i, i + 1 = $j")
            }
            if (toPosition == chosenTime) chosenTime--
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(list, i, i - 1)
                val j = i - 1
                Log.d("swap", "i = $i, i - 1 = $j")
            }
            if (toPosition == chosenTime) chosenTime++
        }
        if (fromPosition == chosenTime) chosenTime = toPosition
        Log.d("move", "chosenTime $chosenTime now")
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
        list.removeAt(position)
        dismissCallback?.dismissItem()
    }

    fun addItem(time: TimeData) {
        list.add(0,time)
        val prev = chosenTime
        chosenTime = 0
        notifyItemChanged(prev)
        notifyItemInserted(0)
    }

    fun addNumb() {
        notifyItemChanged(chosenTime)
    }

    fun clearNumb() {
        notifyItemChanged(chosenTime)
    }
}