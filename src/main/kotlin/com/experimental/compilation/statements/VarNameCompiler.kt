package com.experimental.compilation.statements

import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxType
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.compileFinal
import com.experimental.context.VarName

class VarNameCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(\\w+)\\s*".toRegex()
    }

    override fun getType(): SyntaxType = ContextSyntaxType.VAR_NAME
    override fun compile(code: String): CompileResult {
        return code.compileFinal(REGEX, this::compile)
    }

    fun compile(matchResult: MatchResult): VarName {
        val varName = matchResult.groupValues[1]
        return VarName(varName)
    }

}