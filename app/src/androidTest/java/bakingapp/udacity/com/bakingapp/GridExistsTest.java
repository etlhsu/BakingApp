package bakingapp.udacity.com.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GridExistsTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkExists(){

        //View
        onView((withId(R.id.gv_recipes))).check(matches(isDisplayed()));

        //AdapterView
        onData(anything()).inAdapterView(withId(R.id.gv_recipes)).perform(click());


    }


}
