package com.liquorworldkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import app.cargo.R
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

/**
 * Created by User on 8/18/2016.
 */
class OrderListAdapter(internal var context: Context, arrayList: ArrayList<JSONObject>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    internal lateinit var updateData: UpdateData
    internal var upDateAgain = true
    internal var updateLoader = true
    internal lateinit var parent: ViewGroup

    internal var arrayList = ArrayList<JSONObject>()
    internal var itemClickListener: OnItemClickListener? = null
    //internal lateinit var mySharedPreferance: MySharedPreferance

    init {
        this.arrayList = arrayList
    }

    fun paginate(updateData: UpdateData) {
        this.updateData = updateData
    }

    fun updateAgain(upDateAgain: Boolean) {
        this.upDateAgain = upDateAgain
    }

    fun loader(updateLoader: Boolean) {
        this.updateLoader = updateLoader
    }

    interface UpdateData {
        operator fun get(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            NORMAL -> {
                this.parent = parent
                val view = LayoutInflater.from(parent.context).inflate(R.layout.child_order, parent, false)
                return DataObjectHolder(view)
            }
            FOOTER -> {
                val view1 = LayoutInflater.from(parent.context).inflate(R.layout.pagination_loader, parent, false)
                return FooterProgressbar(view1)
            }
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        println("list position======================>$position")
        //mySharedPreferance = MySharedPreferance(context)

      /* holder..setOnClickListener
        {
            try {
                if (itemClickListener != null) {
                    itemClickListener!!.OnItemClick(position, arrayList[position].getString("id"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }*/


        if (holder is FooterProgressbar) {
            if (updateLoader) {
                holder.ll_loader.visibility = View.GONE
            } else {
                holder.ll_loader.visibility = View.GONE
            }
        } else {
            if (position > 0 && position == arrayList.size - 5) {
                println("the Position pgnton======================>$upDateAgain")
                if (upDateAgain) {
                    println("the Position pgnton exact======================>$position")
                    updateData[arrayList.size]
                }
            }


            try {

                (holder as DataObjectHolder).ll_trac.setOnClickListener {
                    if (itemClickListener != null) {
                        itemClickListener!!.OnItemClick(position, "suj")
                    }
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionFooter(position)) {
            FOOTER
        } else NORMAL
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == arrayList.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    inner class DataObjectHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var ll_trac: LinearLayout


        init {

            ll_trac = itemView.findViewById(R.id.ll_track)

        }
    }

    inner class FooterProgressbar(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var ll_footer: LinearLayout
        internal var ll_loader: LinearLayout


        init {
            ll_footer = itemView.findViewById(R.id.ll_footer)
            ll_loader = itemView.findViewById(R.id.ll_loader)

        }
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener {
        fun OnItemClick(position: Int, order_id: String)
        fun OnItemClickRate(position: Int, order_id: String)
    }

    companion object {

        val NORMAL = 1
        val FOOTER = 2
    }
}
