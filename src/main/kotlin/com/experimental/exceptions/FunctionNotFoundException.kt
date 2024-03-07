package com.experimental.exceptions

class FunctionNotFoundException(name: String) : ExecutionException("Function: $name not found")