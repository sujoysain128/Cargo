
package app.cargo.widgets
import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * Created by ncrts on 13/9/17.
 */

class LatoRegularTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        this.setTypeface(typeface, typeface.style)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.setTypeface(typeface, typeface.style)

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val tf = typeface
        this.setTypeface(typeface, typeface.style)
    }


    override fun setTypeface(tf: Typeface?, style: Int) {
        var tf = tf
        println()
        if (style == 1) {
            //replace "HelveticaBOLD.otf" with the name of your bold font
            tf = Typeface.createFromAsset(context.applicationContext.assets, "LATO-REGULAR.ttf")
        } else {
            //replace "HelveticaNORMAL.otf" with the name of your normal font
            tf = Typeface.createFromAsset(context.applicationContext.assets, "LATO-REGULAR.ttf")
        }
        super.setTypeface(tf, 0)
    }
    //avenirltstd-black
}
