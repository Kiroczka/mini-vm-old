package com.experimental.exceptions

class UnknownComponentException(code: String) :
    CompilationException("Unknown statement or expression: $code")