package com.experimental.compilation

import com.experimental.compilation.factories.BuildersFactory
import com.experimental.compilation.factories.CompilersFactory
import com.experimental.exceptions.CompilationException
import com.experimental.exceptions.UnknownComponentException
import com.experimental.utils.firstNonEmptyLine

abstract class CompilerCoordinator(
    private val compilersFactory: CompilersFactory,
    private val buildersFactory: BuildersFactory
) : Compiler {

    fun addCompiler(partType: PartType, compiler: Compiler) {
        compilersFactory.addCompiler(partType, compiler)
    }

    override fun compile(code: String): SuccessFinalResult {
        compilersFactory.getRootCompilers().forEach { compiler ->
            val result = compiler.compile(code)
            if (result is SuccessCompileResult) {
                return compile(result, compiler.getType())
            }
        }
        throw UnknownComponentException(code.firstNonEmptyLine())
    }

    private fun compile(result: SuccessCompileResult, type: PartType): SuccessFinalResult {
        return when (result) {
            is SuccessFinalResult -> result
            is SuccessRequireMoreCompilationResult -> {
                val element = buildSyntaxElement(result, type)
                SuccessFinalResult(element, result.lastIndex)
            }
        }
    }

    private fun buildSyntaxElement(result: SuccessRequireMoreCompilationResult, type: PartType): SyntaxElement {
        val elements = buildSyntaxElements(result.codeParts)
        val input = BuilderInput(elements)
        val builder = buildersFactory.getBuilder(type)
        return builder.build(input)
    }

    private fun buildSyntaxElements(elements: List<CodeToCompile>): List<SyntaxElement> {
        return elements.map { part ->
            when (val result = chooseCompiler(part.type).compile(part.code)) {
                is SuccessFinalResult -> result.value
                is SuccessRequireMoreCompilationResult -> buildSyntaxElement(result, part.type)
                FailedCompileResult ->
                    throw CompilationException("Unable to compile required part with type: ${part.type} code: ${part.code}")
            }
        }
    }

    private fun chooseCompiler(type: PartType): Compiler {
        if (type == getType()) return this
        return compilersFactory.getCompiler(type)
    }

}
