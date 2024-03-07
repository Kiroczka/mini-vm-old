package com.experimental.compilation.statements

import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.GeneralSyntaxType
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.compileFinal
import com.experimental.components.NothingStatement
import com.experimental.components.Statement

class CommentCompiler : Compiler {

    companion object {
        private val REGEX = "^\\s*//.*".toRegex()
    }

    override fun getType(): SyntaxType = GeneralSyntaxType.COMMENT

    override fun compile(code: String): CompileResult {
        return code.compileFinal(REGEX, this::compile)
    }

    fun compile(ignored: MatchResult): Statement = NothingStatement


}