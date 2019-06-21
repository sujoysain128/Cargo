package app.cargo.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.cargo.R
import app.cargo.adapter.AddressListAdapter
import kotlinx.android.synthetic.main.fragment_address_list.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AddressListFragment : Fragment() {

    internal lateinit var arrayList: ArrayList<JSONObject>
    lateinit var addressListAdapter: AddressListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayList = ArrayList()
        addressListAdapter = AddressListAdapter(activity!!, arrayList,"addressList")
        val mLinearLayoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        rv_address_list.layoutManager = mLinearLayoutManager
        rv_address_list.itemAnimator = DefaultItemAnimator()
        rv_address_list.setHasFixedSize(true)
        rv_address_list.adapter = addressListAdapter
        try {
            loadPrayerData()
            //note_list()
        } catch (e: Exception) {
            e.printStackTrace()
        }



        iv_back.setOnClickListener {
            println("Pick Up Frag========>>>>> " + fragmentManager)
            activity!!.onBackPressed()
        }
    }
    fun loadPrayerData() {
        arrayList.clear()
        arrayList.add(JSONObject().put("ID", 1).put("NAME", "Note1"))
        arrayList.add(JSONObject().put("ID", 2).put("NAME", "Note2"))
        arrayList.add(JSONObject().put("ID", 3).put("NAME", "Note3"))
        arrayList.add(JSONObject().put("ID", 4).put("NAME", "Note4"))
        addressListAdapter.notifyDataSetChanged()
    }

}
