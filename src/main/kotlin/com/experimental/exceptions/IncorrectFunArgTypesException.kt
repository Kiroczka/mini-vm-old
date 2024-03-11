package com.experimental.exceptions

import com.experimental.context.FunName
import com.experimental.context.Type

class IncorrectFunArgTypesException(funName: FunName, argIndex: Int, expected: Type, actual: Type) :
    FunctionInvocationException(funName, "Expected $argIndex argument with type:$expected but got: $actual")