package com.experimental.compilation

interface Compiler {
    fun getType(): PartType

    fun compile(code: String): CompileResult

}
