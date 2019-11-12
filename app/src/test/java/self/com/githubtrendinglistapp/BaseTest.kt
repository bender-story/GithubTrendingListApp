package self.com.githubtrendinglistapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.KoinComponent
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
open class BaseTest :KoinComponent{
    var context: Context?= ApplicationProvider.getApplicationContext()

    @Before
    open fun beforeEach() {
        MockitoAnnotations.initMocks(this)
    }

}