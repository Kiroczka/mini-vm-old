package com.experimental.exceptions

open class ExecutionException(message: String) : RuntimeException("Execution failed with the message: $message")
