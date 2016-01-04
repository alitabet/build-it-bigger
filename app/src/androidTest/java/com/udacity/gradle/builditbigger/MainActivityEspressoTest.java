package com.udacity.gradle.builditbigger;

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
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Android Instrumentation Test to check
 * if AsyncTask return a non-null or
 * non-empty joke string
 *
 * @author Ali K Thabet
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void asyncShouldReturnJoke() {
        // click the joke retrieve button
        onView(withId(R.id.button_get_joke)).perform(click());
        // check that the TextView text in jokesactivity
        // is not empty or not null
        onView(withId(R.id.joke_text_view)).check(matches(withText(not(isEmptyOrNullString()))));
    }
}
