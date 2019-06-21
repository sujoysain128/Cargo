package app.cargo.dilaog_fragment


import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.cargo.R
import kotlinx.android.synthetic.main.signup_dialog_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SignupDialogFragment : DialogFragment() {

    internal lateinit var dialog: Dialog
    internal lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.signup_dialog_fragment, container, false)
        return  v
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = object : Dialog(activity!!,R.style.DialogFragmentCustomTheme) {
            override fun onBackPressed() {
                //super.onBackPressed();
                dismiss()
            }
        }
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.window!!.attributes.windowAnimations = R.style.DialogFragmentCustomTheme
        println("Dialog ONcreate============>")
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_log_in.setOnClickListener {
            dismiss()
        }


        tv_next_page.setOnClickListener {
            val signupDetailsDialogFragment = SignupDetailsDialogFragment()

            signupDetailsDialogFragment.setOnItemClickListener(object :SignupDetailsDialogFragment.OnItemClickListener{
                override fun OnItemClick(position: String, id: String) {
                    dismiss()
                }
            })

            signupDetailsDialogFragment.show(childFragmentManager, "SignupDetailsDialogFragment")
        }
    }


}
