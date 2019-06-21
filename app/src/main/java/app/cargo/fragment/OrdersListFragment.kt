package app.cargo.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.cargo.R
import com.liquorworldkotlin.adapter.OrderListAdapter
import kotlinx.android.synthetic.main.fragment_orders_list.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OrdersListFragment : Fragment(), OrderListAdapter.OnItemClickListener {

    internal lateinit var arrayList: ArrayList<JSONObject>
    lateinit var orderListAdapter: OrderListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayList = ArrayList()

        orderListAdapter = OrderListAdapter(activity!!, arrayList)
        orderListAdapter.setOnItemClickListener(this)
        val mLinearLayoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        rv_order.layoutManager = mLinearLayoutManager
        rv_order.itemAnimator = DefaultItemAnimator()
        rv_order.setHasFixedSize(true)
        rv_order.adapter = orderListAdapter

        try {
            loadPrayerData()
            //note_list()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun loadPrayerData() {
        arrayList.clear()
        arrayList.add(JSONObject().put("ID", 1).put("NAME", "Note1"))
        arrayList.add(JSONObject().put("ID", 2).put("NAME", "Note2"))
        arrayList.add(JSONObject().put("ID", 3).put("NAME", "Note3"))
        arrayList.add(JSONObject().put("ID", 4).put("NAME", "Note4"))
        orderListAdapter.notifyDataSetChanged()
    }

    override fun OnItemClick(position: Int, order_id: String) {
        println("Adapter onItemClick OrdersListFragment")
    }

    override fun OnItemClickRate(position: Int, order_id: String) {

    }
}
