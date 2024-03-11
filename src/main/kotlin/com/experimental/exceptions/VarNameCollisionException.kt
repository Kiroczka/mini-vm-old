package com.experimental.exceptions

import com.experimental.context.VarName

class VarNameCollisionException(name: VarName) : ExecutionException("Variable names collision: $name")