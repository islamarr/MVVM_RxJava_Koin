package com.islam.music.features.main_screen.presentation.view

import android.os.Bundle
import android.view.KeyEvent
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.islam.music.R
import com.islam.music.utils.EspressoIdlingResourceRule
import com.islam.music.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@MediumTest
@RunWith(AndroidJUnit4::class)
class MainScreenFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun check_SearchIcon() {
        launchFragmentInHiltContainer<MainScreenFragment>(Bundle(), R.style.Theme_Music)
        onView(withId(R.id.startSearch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_navigateToSearchScreen() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<MainScreenFragment> {
            navController.setGraph(R.navigation.navigation_project)
            Navigation.setViewNavController(this.requireView(), navController)
        }
        onView(withId(R.id.startSearch)).perform(ViewActions.click())

        assertEquals(navController.currentDestination?.id, R.id.searchFragment)

    }

}