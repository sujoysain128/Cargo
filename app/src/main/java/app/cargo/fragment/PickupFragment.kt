package app.cargo.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.cargo.MainActivity

import app.cargo.R
import kotlinx.android.synthetic.main.fragment_pickup.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PickupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pickup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rr_root_layout.setOnClickListener {
            //Toast.makeText(activity!!, "Hey Sujoy", Toast.LENGTH_LONG).show()
        }

        iv_back.setOnClickListener {
            println("Pick Up Frag========>>>>> " + fragmentManager)
            activity!!.onBackPressed()
        }


        et_pick_up_address.setOnClickListener {
            (activity!! as MainActivity).callFragment(com.excelwallcoverings.Utils.mFragmentManager.PICK_UP_ADDRESS_FRAGMENT)
        }
    }



}
