package com.experimental.context

import com.experimental.compilation.SyntaxElement

class Variable(
    val name: VarName,
    val value: TypedValue,
) {
    override fun toString(): String {
        return "Variable(name=$name, value=$value)"
    }
}

data class VarName(val name: String) : SyntaxElement