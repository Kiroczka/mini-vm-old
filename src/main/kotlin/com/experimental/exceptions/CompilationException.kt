package com.experimental.exceptions

open class CompilationException(message: String) : RuntimeException("Compilation failed with the message: $message")