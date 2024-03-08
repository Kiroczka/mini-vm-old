package com.experimental.exceptions

import com.experimental.context.FunName

open class FunctionInvocationException(funName: FunName, message: String) :
    ExecutionException("Function ${funName.name} execution failed with message: $message")