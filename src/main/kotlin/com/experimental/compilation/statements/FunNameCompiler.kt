package com.experimental.compilation.statements

import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxElement
import com.experimental.compilation.PartType
import com.experimental.compilation.compileFinal
import com.experimental.context.FunName

class FunNameCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(\\w+)\\s*".toRegex()
    }

    override fun getType(): PartType = ContextSyntaxElement.FUN_NAME
    override fun compile(code: String): CompileResult {
        return code.compileFinal(REGEX, this::compile)
    }

    fun compile(matchResult: MatchResult): FunName {
        val funName = matchResult.groupValues[1]
        return FunName(funName)
    }

}