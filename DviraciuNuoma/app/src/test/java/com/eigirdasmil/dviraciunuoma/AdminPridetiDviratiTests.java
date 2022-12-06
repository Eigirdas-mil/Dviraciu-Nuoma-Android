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
public class AdminPridetiDviratiTests {
    @Rule
    public ActivityScenarioRule<AdminPridetiDvirati> adminPridetiDviratiActivityScenario = new ActivityScenarioRule<>(AdminPridetiDvirati.class);

    @Test
    public void AdminPridetiDvirati_RendersCorrectly() {
        onView(withId(R.id.editTextTextPersonName5)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPersonName6)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPersonName8)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPersonName7)).check(matches(isDisplayed()));
        onView(withId(R.id.button7)).check(matches(isDisplayed()));
        onView(withId(R.id.button8)).check(matches(isDisplayed()));
    }

    @Test
    public void AdminPridetiDvirati_ElementsWorking() {
        onView(withId(R.id.editTextTextPersonName5)).perform(typeText("Testas"));
        onView(withId(R.id.editTextTextPersonName6)).perform(typeText("PLENTINIS"));
        onView(withId(R.id.editTextTextPersonName8)).perform(typeText("15"));
        onView(withId(R.id.editTextTextPersonName7)).perform(typeText("testas"));
        onView(withId(R.id.button7)).perform(click());
        onView(withId(R.id.button8)).perform(click());
    }
}
