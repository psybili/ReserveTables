package com.quandoo.reservetables

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.quandoo.reservetables.ui.customers.CustomerActivity
import org.junit.Rule
import org.junit.Test
import sample.onursaygili.githubbrowser.CustomAssertions.Companion.hasItemCount

class CustomerActivityTests {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<CustomerActivity>(CustomerActivity::class.java)

    @Test
    fun testListCustomers() {
        Thread.sleep(1500)
        onView(withId(R.id.recycler_view))
                .perform()
                .check(hasItemCount(21))
    }

}