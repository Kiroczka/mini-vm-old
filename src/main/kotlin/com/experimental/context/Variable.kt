package com.experimental.context

import com.experimental.compilation.SyntaxElement

data class Variable(
    val name: VarName,
    val value: TypedValue,
)
data class VarName(val name: String) : SyntaxElement