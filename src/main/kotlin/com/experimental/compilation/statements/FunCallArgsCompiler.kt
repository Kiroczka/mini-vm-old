package com.experimental.compilation.statements

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ExpressionSyntaxElement
import com.experimental.compilation.GeneralSyntaxElement
import com.experimental.compilation.PartType
import com.experimental.compilation.SuccessFinalResult
import com.experimental.compilation.SuccessRequireMoreCompilationResult
import com.experimental.components.expressions.FunCallArgs

class FunCallArgsCompiler : Compiler {
    override fun getType(): PartType = ExpressionSyntaxElement.FUN_CALL_ARGS

    override fun compile(code: String): CompileResult {
        if (code.isBlank()) {
            return SuccessFinalResult(FunCallArgs(), code.lastIndex)
        }
        val elements = code.split(",").map {
            CodeToCompile(GeneralSyntaxElement.EXPRESSION, it)
        }

        return SuccessRequireMoreCompilationResult(elements, code.lastIndex)
    }


}