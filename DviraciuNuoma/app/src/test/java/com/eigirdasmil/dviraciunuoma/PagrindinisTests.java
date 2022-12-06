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
public class PagrindinisTests {
    @Rule
    public ActivityScenarioRule<Pagrindinis> pagrindinisActivityScenario = new ActivityScenarioRule<>(Pagrindinis.class);

    @Test
    public void Pagrindinis_RendersCorrectly() {
        onView(withId(R.id.spinner2)).check(matches(isDisplayed()));
        onView(withId(R.id.textView8)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
        onView(withId(R.id.button9)).check(matches(isDisplayed()));
    }

    @Test
    public void Pagrindinis_ElementsWorking() {
        onView(withId(R.id.spinner2)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.button9)).perform(click());
    }
}
