package self.com.githubtrendinglistapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import self.com.githubtrendinglistapp.di.appModule

@RunWith(RobolectricTestRunner::class)
open class BaseTest : KoinTest {
    var context: Context?= ApplicationProvider.getApplicationContext<MyApplication>()

    @Before
    open fun beforeEach() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    open fun afterEach() {
        stopKoin()
    }

}