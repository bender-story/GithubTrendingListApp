package self.com.githubtrendinglistapp.view


import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import self.com.githubtrendinglistapp.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(
            allOf(
                withText("Trending"),
                childAtPosition(
                    allOf(
                        withId(R.id.header),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Trending")))

        Thread.sleep(1500)
        onView(withId(R.id.mainRecyclerView))
            .check(matches(atPosition(0, hasDescendant(withText("async-std")))))

        onView(withId(R.id.mainRecyclerView))
            .check(matches(atPosition(0, hasDescendant(withText("1405")))))

        onView(withId(R.id.mainRecyclerView))
            .check(matches(atPosition(0, hasDescendant(withText("129")))))

        Thread.sleep(1500)
        // Scroll to bottom of the recycler view
        val recyclerView = mActivityTestRule.activity.mainRecyclerView
        val itemCount = recyclerView.adapter!!.itemCount
        // Scroll to end of page with position
        onView(withId(R.id.mainRecyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    private fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}
