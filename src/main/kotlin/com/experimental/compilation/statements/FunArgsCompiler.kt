package com.experimental.compilation.statements

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxType
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.StatementSyntaxType
import com.experimental.compilation.SuccessFinalResult
import com.experimental.compilation.RequireMoreCompilationResult
import com.experimental.context.Arguments

class FunArgsCompiler : Compiler {
    override fun getType(): SyntaxType = ContextSyntaxType.ARGS

    override fun compile(code: String): CompileResult {
        if (code.isBlank()) {
            return SuccessFinalResult(Arguments(), code.lastIndex)
        }
        val elements = code.split(",").map {
            CodeToCompile(StatementSyntaxType.VAR_DECLARATION, it)
        }

        return RequireMoreCompilationResult(elements, code.lastIndex)
    }


}