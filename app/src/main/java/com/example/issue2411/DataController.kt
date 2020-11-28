package com.example.issue2411

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*

class DataController {
    val job = SupervisorJob()
    val dispatcher = DispatcherProvider.Main
    val scope = CoroutineScope(job + dispatcher)

    private val stringListFlow = MutableStateFlow<List<String>>(emptyList())
    private val intFlow = MutableStateFlow<Int?>(null)

    private val combinedFlow: StateFlow<Int?> =
        intFlow.combine(stringListFlow) { i: Int?, s: List<String> ->
            computeCount(s, i)
        }.stateIn(scope, SharingStarted.Eagerly, 0)

    val combinedLiveData = combinedFlow.asLiveData()

    private fun computeCount(strings: List<String>, num: Int?): Int? =
        num?.let { strings.count() + it }

    fun upsertString(string: String) {
        stringListFlow.value = stringListFlow.value.toMutableList().apply { add(string) }
    }

    fun incrementInt() {
        intFlow.value = intFlow.value?.inc() ?: 1
    }
}
