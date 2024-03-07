package com.experimental.compilation.factories

import com.experimental.compilation.Compiler
import com.experimental.compilation.PartType
import com.experimental.exceptions.CompilerNotFoundException

class CompilersFactory(
    private val rootCompilers: List<Compiler>,
    compilers: List<Compiler>
) {
    private val compilers: MutableMap<PartType, Compiler> =
        compilers.groupBy { it.getType() }.mapValues { it.value.first() }.toMutableMap()

    fun getCompiler(type: PartType): Compiler = compilers[type] ?: throw CompilerNotFoundException(type)
    fun getRootCompilers(): List<Compiler> = rootCompilers
    fun addCompiler(partType: PartType, compiler: Compiler) {
        compilers[partType] = compiler
    }
}