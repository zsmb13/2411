package com.example.issue2411

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*

class DataController(dispatcher: CoroutineDispatcher) {
    val scope = CoroutineScope(SupervisorJob() + dispatcher)

    private val flowA = MutableStateFlow<Int>(0)
    private val flowB = MutableStateFlow<Int>(0)

    val combinedFlow: StateFlow<Int?> =
        flowA.combine(flowB) { a: Int, b: Int -> a + b }
            .stateIn(scope, SharingStarted.Eagerly, 0)

    fun incrementA() {
        flowA.value++
    }

    fun incrementB() {
        flowB.value++
    }
}
