package com.example.myapplication.recyclerview

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.TimeData


class ItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!), ItemTouchHelperViewHolder {
    val textView: TextView?

    init {
        textView = itemView?.findViewById(R.id.text)
    }

    fun bind(time: TimeData?, chosenTime: Int) {
        textView!!.text = time.toString()

        //switchBackground(chosenTime, textView)
        switchTextColor(chosenTime, textView)
    }

    private fun switchTextColor(chosenTime: Int, textView: TextView) {
        if (chosenTime == adapterPosition) textView.setTextColor(
            ContextCompat.getColor(
                textView.context,
                R.color.text_selected
            )
        )
        else textView.setTextColor(ContextCompat.getColor(textView.context, R.color.text))
    }

    private fun switchBackground(chosenTime: Int, textView: TextView) {
        if (chosenTime == adapterPosition) textView.setBackgroundColor(
            ContextCompat.getColor(
                textView.context,
                R.color.purple_200
            )
        )
        else textView.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.item_background))
    }

    @SuppressLint("ResourceAsColor")
    override fun onItemSelected() {
        /*val prev = textView
        textView?.let { ContextCompat.getColor(it.context, R.color.purple_500) }
            ?.let { textView.setBackgroundColor(it) }
        */
    }

    override fun onItemClear() {
        /*val cd : ColorDrawable = textView?.background as ColorDrawable
        val colorCode = cd.color*/
        /*if (!selected) textView?.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.white))
        else textView?.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.purple_200))*/
        //textView.setBackgroundColor(colorCode)

        textView?.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.item_background))


    }

}