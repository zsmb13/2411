package com.example.issue2411

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.yield
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IssueTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `test`() = testCoroutineRule.scope.runBlockingTest {
        DataController().let { controller ->
            yield()

            // we're using a test coroutine dispatcher
            Truth.assertThat(controller.dispatcher)
                .isInstanceOf(TestCoroutineDispatcher::class.java)

            // should start null if combine runs after stateIn sets 0
            val initialValue = controller.combinedLiveData.getOrAwaitValue()
            Truth.assertThat(initialValue).isEqualTo(null)

            // should be 1 after increment
            controller.incrementInt()
            val currentValue = controller.combinedLiveData.getOrAwaitValue()

            // should definitely not be null at this point...
            // using breakpoints, seems combine function runs after this assert???
            Truth.assertThat(currentValue).isNotNull()
        }
    }
}
