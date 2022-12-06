package com.eigirdasmil.dviraciunuoma;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AdminControlTests {
    @Rule
    public ActivityScenarioRule<AdminControl> adminControlActivityScenario = new ActivityScenarioRule<>(AdminControl.class);

    @Test
    public void AdminControl_RendersCorrectly() {
        onView(withId(R.id.spinner)).check(matches(isDisplayed()));
        onView(withId(R.id.button5)).check(matches(isDisplayed()));
        onView(withId(R.id.button6)).check(matches(isDisplayed()));
    }

    @Test
    public void AdminControl_ElementsWorking() {
        onView(withId(R.id.spinner)).perform(click());
        onView(withId(R.id.button5)).perform(click());
        onView(withId(R.id.button6)).perform(click());
    }
}
