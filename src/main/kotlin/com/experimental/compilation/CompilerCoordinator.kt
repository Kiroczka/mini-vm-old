package com.experimental.compilation

import com.experimental.compilation.factories.BuildersFactory
import com.experimental.compilation.factories.CompilersFactory
import com.experimental.exceptions.CompilationException
import com.experimental.exceptions.UnknownComponentException

abstract class CompilerCoordinator(
    private val compilersFactory: CompilersFactory,
    private val buildersFactory: BuildersFactory
) : Compiler {

    fun addCompiler(syntaxType: SyntaxType, compiler: Compiler) {
        compilersFactory.addCompiler(syntaxType, compiler)
    }

    override fun compile(code: String): SuccessFinalResult {
        compilersFactory.getRootCompilers().forEach { compiler ->
            val result = compiler.compile(code)
            if (result is SuccessCompileResult) {
                return compile(result, compiler.getType())
            }
        }
        throw UnknownComponentException(code)
    }

    private fun compile(result: SuccessCompileResult, type: SyntaxType): SuccessFinalResult {
        return when (result) {
            is SuccessFinalResult -> result
            is SuccessRequireMoreCompilationResult -> {
                val element = buildSyntaxElement(result, type)
                SuccessFinalResult(element, result.lastIndex)
            }
        }
    }

    private fun buildSyntaxElement(result: SuccessRequireMoreCompilationResult, type: SyntaxType): SyntaxElement {
        val elements = buildSyntaxElements(result.codeParts)
        val input = BuilderInput(elements)
        val builder = buildersFactory.getBuilder(type)
        return builder.build(input)
    }

    private fun buildSyntaxElements(elements: List<CodeToCompile>): List<SyntaxElement> {
        return elements.map { element ->
            when (val result = chooseCompiler(element.type).compile(element.code)) {
                is SuccessFinalResult -> result.value
                is SuccessRequireMoreCompilationResult -> buildSyntaxElement(result, element.type)
                FailedCompileResult ->
                    throw CompilationException("Unable to compile required syntax element with type: ${element.type} code: ${element.code}")
            }
        }
    }

    private fun chooseCompiler(type: SyntaxType): Compiler {
        if (type == getType()) return this
        return compilersFactory.getCompiler(type)
    }

}
