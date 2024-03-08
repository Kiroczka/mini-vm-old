package com.experimental.compilation.statements

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ExpressionSyntaxType
import com.experimental.compilation.GeneralSyntaxType
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.SuccessFinalResult
import com.experimental.compilation.RequireMoreCompilationResult
import com.experimental.components.expressions.FunCallArgs

class FunCallArgsCompiler : Compiler {
    override fun getType(): SyntaxType = ExpressionSyntaxType.FUN_CALL_ARGS

    override fun compile(code: String): CompileResult {
        if (code.isBlank()) {
            return SuccessFinalResult(FunCallArgs(), code.lastIndex)
        }
        val elements = code.split(",").map {
            CodeToCompile(GeneralSyntaxType.EXPRESSION, it)
        }

        return RequireMoreCompilationResult(elements, code.lastIndex)
    }


}