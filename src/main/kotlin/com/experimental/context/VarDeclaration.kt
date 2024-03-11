package com.experimental.context

import com.experimental.compilation.SyntaxElement

data class VarDeclaration(
    val varName: VarName,
    val type: Type?
) : SyntaxElement