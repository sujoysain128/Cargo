package com.excelwallcoverings.Utils

import android.support.v4.app.Fragment
import app.cargo.fragment.*


class mFragmentManager {

    var arrStack: MutableList<Int> = ArrayList()

    companion object {
        val PICKUP_FRAGMENT = 2
        val HOME_FRAGMENT = 1
        val PICK_UP_ADDRESS_FRAGMENT = 3
        val ADDRESS_FRAGMENT = 4
        val ORDER_FRAGMENT = 5


    }

    var pickupFragment: PickupFragment? = null
    var homeFragment: HomeFragment? = null
    var pickupAddressFragment: PickupAddressFragment? = null
    var addressFragment: AddressListFragment? = null
    var ordersListFragment: OrdersListFragment? = null




    fun clearFragmentStack() {
        arrStack.clear()
        pickupFragment = null
        homeFragment = null
        pickupAddressFragment = null
        addressFragment = null
        ordersListFragment = null

    }

    fun clearThisFragment(tag: Int) {
        when (tag) {
            PICKUP_FRAGMENT -> pickupFragment = null
            HOME_FRAGMENT -> homeFragment = null
            PICK_UP_ADDRESS_FRAGMENT -> pickupAddressFragment = null
            ADDRESS_FRAGMENT -> addressFragment = null
            ORDER_FRAGMENT -> ordersListFragment = null

        }
    }

    fun removeThisFragment(tag: Int) {
        when (tag) {
            PICKUP_FRAGMENT -> arrStack.remove(PICKUP_FRAGMENT)
            HOME_FRAGMENT -> arrStack.remove(HOME_FRAGMENT)
            PICK_UP_ADDRESS_FRAGMENT -> arrStack.remove(PICK_UP_ADDRESS_FRAGMENT)
            ADDRESS_FRAGMENT -> arrStack.remove(ADDRESS_FRAGMENT)
            ORDER_FRAGMENT -> arrStack.remove(ORDER_FRAGMENT)

        }
    }

    private fun generateFragment(tag: Int, fragment: Fragment?, isNew: Boolean): Fragment {

        if (fragment == null || isNew) {
            println("-------Generating New Fragment Instance------$isNew")
            when (tag) {
                PICKUP_FRAGMENT -> return PickupFragment().also { pickupFragment = it }
                HOME_FRAGMENT -> return HomeFragment().also { homeFragment = it }
                PICK_UP_ADDRESS_FRAGMENT -> return PickupAddressFragment().also { pickupAddressFragment = it }
                ADDRESS_FRAGMENT -> return AddressListFragment().also { addressFragment = it }
                ORDER_FRAGMENT -> return OrdersListFragment().also { ordersListFragment = it }
               }
        }
        println("-------Return Old Fragment Instance------")
        return fragment
    }

    fun openFragment(tag: Int, isNew: Boolean = false): Fragment {
        when (tag) {
            PICKUP_FRAGMENT -> return generateFragment(tag, pickupFragment, isNew)
            HOME_FRAGMENT -> return generateFragment(tag, homeFragment, isNew)
            PICK_UP_ADDRESS_FRAGMENT -> return generateFragment(tag, pickupAddressFragment, isNew)
            ADDRESS_FRAGMENT -> return generateFragment(tag, addressFragment, isNew)
            ORDER_FRAGMENT -> return generateFragment(tag, ordersListFragment, isNew)
           }
        return Fragment()
    }


    fun addStackFragment(tag: Int) {
        if (arrStack.size > 0) {
            if (arrStack[arrStack.size - 1] != tag) {
                arrStack.add(tag)
            }
        } else {
            arrStack.add(tag)
        }

    }

    fun hasFragmentInStack(): Boolean {
        return arrStack.size > 1
    }


    fun openPreviousFragment(isNew: Boolean = false): Fragment {
        arrStack.removeAt(arrStack.size - 1)
        return openFragment(arrStack[arrStack.size - 1], isNew)

    }

}
