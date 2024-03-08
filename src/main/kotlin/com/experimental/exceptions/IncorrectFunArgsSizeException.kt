package com.experimental.exceptions

import com.experimental.context.FunName

class IncorrectFunArgsSizeException(funName: FunName, expectedSize: Int, actualSize: Int) :
    FunctionInvocationException(funName, "Expected: $expectedSize arguments but got: $actualSize")