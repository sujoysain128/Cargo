package app.cargo.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import app.cargo.R
import org.json.JSONObject
import java.util.*


class AddressListAdapter(private val context: Context, arrayList: ArrayList<JSONObject>, flag:String) :
    RecyclerView.Adapter<AddressListAdapter.DataObjectHolder>() {

    private var arrayList = ArrayList<JSONObject>()
    internal var itemClickListener: OnItemClickListener? = null
    private var call = " "

    init {
        this.arrayList = arrayList
        this.call = flag
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataObjectHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_address_list, parent, false)
        return DataObjectHolder(view)
    }

    override fun onBindViewHolder(holder: DataObjectHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    inner class DataObjectHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       /* internal var tv_prayer_name: TextView
        internal var ll_prayer_cell: LinearLayout

        init {
            tv_prayer_name = itemView.findViewById(R.id.tv_prayer_name)
            ll_prayer_cell = itemView.findViewById(R.id.ll_prayer_cell)
        }*/
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener {
        fun OnItemClick(position: String,id:String)
    }

}
