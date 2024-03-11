package com.experimental.exceptions

import com.experimental.context.Type
import com.experimental.context.VarName

class VariableHasIncorrectTypeException(varName: VarName, expectedType: Type, actualType: Type) :
    ExecutionException("Variable: ${varName.name} was expected to have type: $expectedType, but has type: $actualType")