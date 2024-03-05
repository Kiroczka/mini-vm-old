package com.experimental.exceptions

class UnknownTypeException(type: String) : CompilationException("Unknown type: $type") {
}