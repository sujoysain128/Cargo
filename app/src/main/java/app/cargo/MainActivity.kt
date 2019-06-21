package app.cargo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import app.cargo.fragment.PickupFragment
import app.cargo.utils.OnBackPressed
import com.excelwallcoverings.Utils.mFragmentManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.leftpanel.*
import java.lang.Double
import java.util.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMarkerDragListener {

    internal var current_lat = "29.31170"
    internal var current_long = "47.4818"
    internal var store_lat = "29.3117-33.5"
    internal var store_long = "47.4818"
    private var mMap: GoogleMap? = null
    private var mLocationProviderClient: FusedLocationProviderClient? = null
    lateinit var custom_fragment_manager: mFragmentManager
    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var currentFragment: Fragment
    lateinit var addresslist :List<Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_track) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        ll_order.setOnClickListener {
            custom_fragment_manager = mFragmentManager()

            //becz alreafy open fragment is still showing

            callFragment(mFragmentManager.HOME_FRAGMENT)
            val bundle = Bundle()
            if (drawer_menu.isDrawerOpen(ll_left_drawer)) {
                drawer_menu.closeDrawer(ll_left_drawer)
            }
            callFragmentforLeftPanel(mFragmentManager.ORDER_FRAGMENT,bundle)
        }

        iv_menu.setOnClickListener {
            if (drawer_menu.isDrawerOpen(ll_left_drawer)) {
                drawer_menu.closeDrawer(ll_left_drawer)
            } else {
                drawer_menu.openDrawer(ll_left_drawer)
            }
        }


        iv_back.setOnClickListener {
            if (drawer_menu.isDrawerOpen(GravityCompat.START)) {
                drawer_menu.closeDrawer(GravityCompat.START)
            } else {
                if (currentFragment is OnBackPressed) {
                    (currentFragment as OnBackPressed).onBackPressed()
                } else {
                    callBackPressed()
                }
            }
        }


        tv_request.setOnClickListener {
            custom_fragment_manager = mFragmentManager()
            callFragment(mFragmentManager.PICKUP_FRAGMENT)
        }

        iv_collapse.setOnClickListener {
            ll_settings.visibility = View.GONE
            iv_expand.visibility = View.VISIBLE
            iv_collapse.visibility = View.GONE
        }
        iv_expand.setOnClickListener {
            ll_settings.visibility = View.VISIBLE
            iv_collapse.visibility = View.VISIBLE
            iv_expand.visibility = View.GONE
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
        mMap?.uiSettings?.isMapToolbarEnabled = false
        val sydney = LatLng(current_lat.toDouble(), current_long.toDouble())
        val markerOptions = MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.mappin)).draggable(true)
        mMap?.addMarker(markerOptions.position(sydney).title("Location").draggable(true))
        //mMap?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.5f))
        mMap?.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        println("Google map value=======>>>>>> "+p0 + "cheeeee====>>>>>>> "+mMap)
        p0!!.setOnMarkerClickListener(this);
        p0.setOnMarkerDragListener(this)
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                return
            }
            mLocationProviderClient?.lastLocation
                ?.addOnSuccessListener(this, OnSuccessListener<Location> { location ->
                    if (location != null) {
                        val myLatLng1 = LatLng(
                            Double.parseDouble(current_lat),
                            Double.parseDouble(current_long)

                        )
                        /*val markerOptions1 =
                            MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.user_marker))
                        mMap?.addMarker(markerOptions1.position(myLatLng1).title("Customer Location"))
                        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng1, 16f))*/


                        val myLatLng = LatLng(
                            Double.parseDouble(store_lat),
                            Double.parseDouble(store_long)
                        )
                        /* val markerOptions =
                             MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_pin_store_location))*/
                        val markerOptions =
                            MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.mappin))
                        mMap?.addMarker(markerOptions.position(myLatLng).title("My location").draggable(true))
                        //mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 16f))

                    }


                    /*p0!!.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener{
                        override fun onMarkerDragEnd(p0: Marker?) {
                            println("drag end in map")
                        }

                        override fun onMarkerDragStart(p0: Marker?) {
                            println("drag start in map")
                        }

                        override fun onMarkerDrag(p0: Marker?) {
                            println("drag  in map")
                        }

                    })*/

                    mMap = p0



                    /* val origin =
                         LatLng(java.lang.Double.parseDouble(current_lat), java.lang.Double.parseDouble(current_long))

                     if (store_lat.length > 1) {
                         val dest =
                             LatLng(java.lang.Double.parseDouble(store_lat), java.lang.Double.parseDouble(store_long))
                         val url = getUrl(origin, dest)
                         println("onMapClick========>>>>$url")
                         val FetchUrl = FetchUrl()
                         FetchUrl.execute(url)
                     }*/

                    /*if (current_lat>store_lat)
                    {
                        maxLat = current_lat
                    }
                    else{
                        maxLat = store_lat
                    }
                    if (current_long>store_long)
                    {
                        maxLong = current_long
                    }
                    else{
                        maxLong = store_long
                    }
                    val myLatLng = LatLng(
                        java.lang.Double.parseDouble(maxLat),
                        java.lang.Double.parseDouble(maxLong)
                    )
                    mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 16f))*/


                })


        } catch (ex0: Exception) {
            ex0.printStackTrace()
        }


    }


    fun drawerimageset(status: String) {
        if (status.equals("1", ignoreCase = true)) {
            iv_menu.visibility = View.VISIBLE
            iv_back.visibility = View.GONE
            drawer_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        } else if (status.equals("0", ignoreCase = true)) {
            iv_menu.visibility = View.GONE
            iv_back.visibility = View.VISIBLE
            drawer_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
        else if (status.equals("2", ignoreCase = true)) {
            iv_menu.visibility = View.GONE
            iv_back.visibility = View.GONE
        }
    }


    fun callFragmentforLeftPanel(tagFragment: Int, bundle: Bundle, isNew: Boolean = false) {
        currentFragment = custom_fragment_manager.openFragment(tagFragment, isNew)
        currentFragment.arguments = bundle
        custom_fragment_manager.addStackFragment(tagFragment)
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.disallowAddToBackStack()
        /*fragmentTransaction.setCustomAnimations(
            R.animator.fragment_slide_left_enter,
            R.animator.fragment_slide_left_exit,
            R.animator.fragment_slide_right_enter,
            R.animator.fragment_slide_right_exit
        )*/
        fragmentTransaction.replace(R.id.rl_dashboard, currentFragment)
        fragmentTransaction.commit()
    }


    fun callFragment(tagFragment: Int, isNew: Boolean = false) {
        println("callFragment "+tagFragment)
        currentFragment = custom_fragment_manager.openFragment(tagFragment, isNew)
        custom_fragment_manager.addStackFragment(tagFragment)
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.disallowAddToBackStack()
       /* fragmentTransaction.setCustomAnimations(
            R.animator.fragment_slide_left_enter,
            R.animator.fragment_slide_left_exit,
            R.animator.fragment_slide_right_enter,
            R.animator.fragment_slide_right_exit
        )*/
        fragmentTransaction.replace(R.id.container, currentFragment)
        fragmentTransaction.commit()
    }


    fun callBackPressed(isNew: Boolean = false) {
        try {

            if (custom_fragment_manager.hasFragmentInStack()) {
                currentFragment = custom_fragment_manager.openPreviousFragment(isNew)
                println("callBackPressed | MainActivity 3=====>>>>> "+currentFragment)
                if (currentFragment is PickupFragment) {
                    /*custom_fragment_manager.clearFragmentStack()
                    fragmentManager.popBackStackImmediate()
                    println("callBackPressed | MainActivity 2=====>>>>> ")
                    callFragment(mFragmentManager.HOME_FRAGMENT)*/
                    callFragment(mFragmentManager.PICKUP_FRAGMENT)

                    println("callBackPressed | MainActivity 2=====>>>>> ")

                } else {
                    fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.disallowAddToBackStack()

                    fragmentTransaction.replace(R.id.container, currentFragment)
                    fragmentTransaction.commit()
                }

            } else {
                callFragment(mFragmentManager.HOME_FRAGMENT)
                println("callBackPressed | MainActivity=====>>>>> ")
               /* val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle("Do you want to exit?")
                dialog.setPositiveButton("Yes") { dialogInterface, i -> finish() }
                dialog.setNegativeButton("Cancel", null)
                dialog.create().show()*/
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun removeFragment(fragmentTag: Int) {
        custom_fragment_manager.removeThisFragment(fragmentTag)
    }


    fun clearFragment(fragmentTag: Int) {
        custom_fragment_manager.clearThisFragment(fragmentTag)
    }


    fun showSnackBarMessage(message: String) {
        Snackbar.make(drawer_menu, message, Snackbar.LENGTH_LONG).show()
    }

    fun clearAllFragment()
    {
        custom_fragment_manager.clearFragmentStack()
    }

    override fun onBackPressed() {
        if (drawer_menu.isDrawerOpen(GravityCompat.START)) {
            drawer_menu.closeDrawer(GravityCompat.START)
        } else {
            if (currentFragment is OnBackPressed) {
                (currentFragment as OnBackPressed).onBackPressed()
            } else {
                callBackPressed()
            }
        }
    }


    override fun onMarkerDragEnd(p0: Marker?) {
        val myLatLng1 = LatLng(p0!!.position.latitude,p0!!.position.longitude)
        println("marker drag end =====>>>>> "+p0!!.position.latitude)
        val geo =  Geocoder(this, Locale.getDefault())
        addresslist = geo.getFromLocation(p0!!.position.latitude, p0!!.position.longitude, 1)
        println("location address======>>> "+addresslist.get(0).getAddressLine(0) + "city in Location=>>>> "+addresslist.get(0).locality +"admin==>>"+addresslist.get(0).adminArea+ "country==>>"+addresslist.get(0).getCountryName()+"postal code==>>"+addresslist.get(0).postalCode + "known name==>>"+addresslist.get(0).featureName)
    }

    override fun onMarkerDragStart(p0: Marker?) {
        println("marker drag start")
    }

    override fun onMarkerDrag(p0: Marker?) {
        println("marker drag ")
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
