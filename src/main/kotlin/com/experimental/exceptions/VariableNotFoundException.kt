package com.experimental.exceptions

class VariableNotFoundException(name: String) : ExecutionException("Variable: $name not found")