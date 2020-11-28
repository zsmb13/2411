package com.example.issue2411

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

public object DispatcherProvider {

    public var Main: CoroutineDispatcher = Dispatchers.Main
        internal set

    public var IO: CoroutineDispatcher = Dispatchers.IO
        internal set

    /**
     * Overrides the main (UI thread) and IO dispatcher. For testing purposes only.
     */
    public fun set(mainDispatcher: CoroutineDispatcher, ioDispatcher: CoroutineDispatcher) {
        Main = mainDispatcher
        IO = ioDispatcher
    }

    /**
     * Resets the dispatchers to their default values. For testing purposes only.
     */
    public fun reset() {
        Main = Dispatchers.Main
        IO = Dispatchers.IO
    }
}
