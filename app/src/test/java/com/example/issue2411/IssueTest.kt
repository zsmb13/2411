package com.example.issue2411

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class IssueTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `test`() = runBlockingTest {
        DataController(testCoroutineRule.dispatcher).let { controller ->
            // should start null if combine runs after stateIn sets 0
            val initialValue = controller.combinedFlow.value
            println("Initial $initialValue")
            Truth.assertThat(initialValue).isEqualTo(0)

            // should be 1 after increment
            controller.incrementA()
            val currentValue = controller.combinedFlow.value
            println("Current $currentValue")
            Truth.assertThat(currentValue).isEqualTo(1)
        }
    }
}
