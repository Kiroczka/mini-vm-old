package com.experimental.compilation.factories

import com.experimental.compilation.Compiler
import com.experimental.compilation.SyntaxType
import com.experimental.exceptions.CompilerNotFoundException

class CompilersFactory(
    private val rootCompilers: List<Compiler>,
    compilers: List<Compiler>
) {
    private val compilers: MutableMap<SyntaxType, Compiler> =
        compilers.groupBy { it.getType() }.mapValues { it.value.first() }.toMutableMap()

    fun getCompiler(type: SyntaxType): Compiler = compilers[type] ?: throw CompilerNotFoundException(type)
    fun getRootCompilers(): List<Compiler> = rootCompilers
    fun addCompiler(syntaxType: SyntaxType, compiler: Compiler) {
        compilers[syntaxType] = compiler
    }
}