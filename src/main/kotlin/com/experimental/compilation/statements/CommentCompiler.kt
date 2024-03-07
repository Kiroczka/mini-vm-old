package com.experimental.compilation.statements

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxElement
import com.experimental.compilation.GeneralSyntaxElement
import com.experimental.compilation.PartType
import com.experimental.compilation.StatementSyntaxElement
import com.experimental.compilation.SuccessFinalResult
import com.experimental.compilation.SuccessRequireMoreCompilationResult
import com.experimental.context.Arguments

class CommentCompiler : Compiler {
    override fun getType(): PartType = GeneralSyntaxElement.COMMENT

    override fun compile(code: String): CompileResult {
        if (code.isBlank()) {
            return SuccessFinalResult(Arguments(), code.lastIndex)
        }
        val elements = code.split(",").map {
            CodeToCompile(StatementSyntaxElement.VAR_DECLARATION, it)
        }

        return SuccessRequireMoreCompilationResult(elements, code.lastIndex)
    }


}