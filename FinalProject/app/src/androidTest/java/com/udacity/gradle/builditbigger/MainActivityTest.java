package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkJokeTextIsNotEmpty() {
        CountingIdlingResource mainActivityIdlingResource = mActivityRule.getActivity().getEspressoIdlingResourceForMainActivity();

        // registering MainActivity's idling resource for enabling Espresso sync with MainActivity's background threads
        Espresso.registerIdlingResources(mainActivityIdlingResource);

        onView(withId(R.id.btnAndroidJoke))
                .perform(click());

        onView(withId(R.id.tvJoke))
                // check it's not the default text
                .check(matches(not(withText(R.string.tvJoke))))
                // check the retrieved joke is a non-empty string
                .check(matches(not(withText(""))))
                // check the string is not the failed to connect error from app engine
                .check(matches(not(withText(containsString(InstrumentationRegistry.getTargetContext().getString(R.string.appengine_fail_to_connect))))));

        Espresso.unregisterIdlingResources(mainActivityIdlingResource);
    }
}
