package com.kinetx.recyclerviews

import android.content.Context
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class RVIconTextSimple(val listener: OnItemSelectListener) : RecyclerView.Adapter<RVIconTextSimple.MyViewHolder>() {

    private var _list = emptyList<Triple<Int, Int,String>>()
    private var imageHeight : Int = 150
    private var imageWidth : Int = 150

    fun Context.dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics
        ).toInt()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        val itemPicture : ImageView = itemView.findViewById(R.id.item_image)
        val itemText : TextView = itemView.findViewById(R.id.item_text)
        val itemCard : MaterialCardView = itemView.findViewById(R.id.item_card)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!= RecyclerView.NO_POSITION)
            {
                listener.onItemClick(position)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if(position!= RecyclerView.NO_POSITION)
            {
                listener.onItemLongClick(position)
                return true
            }
            return false
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.picture_text_simple,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = _list[position]
        holder.itemPicture.setImageResource(currentItem.first)
        holder.itemCard.setBackgroundColor(currentItem.second)
        holder.itemText.text = currentItem.third
        val layoutParams = holder.itemPicture.layoutParams
        Log.i("III",layoutParams.height.toString())
        layoutParams.height =  holder.itemView.context.dpToPx(imageHeight)
        layoutParams.width = holder.itemView.context.dpToPx(imageWidth)
    }

    override fun getItemCount(): Int {
        return _list.count()
    }

    interface OnItemSelectListener {
        fun onItemClick(position: Int)
        {

        }
        fun onItemLongClick(position: Int)
        {

        }
    }

    fun setData( c : List<Triple<Int, Int,String>>)
    {
        this._list = c
        notifyDataSetChanged()
    }

    fun setImageSize(height: Int, width : Int)
    {
        imageHeight = height
        imageWidth = width
        notifyDataSetChanged()
    }


}