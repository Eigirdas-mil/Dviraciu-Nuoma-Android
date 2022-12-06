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
public class ActivityMainTests {
    @Rule
    public ActivityScenarioRule<MainActivity> prisijungimasActivityScenario = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void Prisijungimas_RendersCorrectly() {
        onView(withId(R.id.editTextTextPersonName)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.button)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
    }

    @Test
    public void Prisijungimas_ElementsWorking() {
        onView(withId(R.id.editTextTextPersonName)).perform(typeText("Testas"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("Testas@testas.testas"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button2)).perform(click());
    }
}
