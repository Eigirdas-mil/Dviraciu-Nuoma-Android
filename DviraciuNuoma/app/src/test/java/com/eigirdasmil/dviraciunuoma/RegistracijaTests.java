package com.eigirdasmil.dviraciunuoma;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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
public class RegistracijaTests {
    @Rule
    public ActivityScenarioRule<Registracija> registracijaActivityScenario = new ActivityScenarioRule<>(Registracija.class);

    @Test
    public void Registracija_RendersCorrectly() {
        onView(withId(R.id.editTextTextPersonName2)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPersonName3)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPersonName4)).check(matches(isDisplayed()));
        onView(withId(R.id.button4)).check(matches(isDisplayed()));
        onView(withId(R.id.button11)).check(matches(isDisplayed()));
    }

    @Test
    public void Registracija_ElementsWorking() {
        onView(withId(R.id.editTextTextPersonName2)).perform(typeText("Testas"));
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText("Testas@testas.testas"));
        onView(withId(R.id.editTextTextPersonName4)).perform(typeText("password"));
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.button11)).perform(click());
    }
}
