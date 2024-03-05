package com.experimental.impl

import com.experimental.compilation.ExpressionCompiler
import com.experimental.compilation.MultipleStatementsCompiler
import com.experimental.compilation.StatementCompiler
import com.experimental.compilation.TypeCompiler
import com.experimental.compilation.expressions.ArithmeticCompiler
import com.experimental.compilation.expressions.FunctionCallExpressionCompiler
import com.experimental.compilation.expressions.LiteralCompiler
import com.experimental.compilation.expressions.VariableCompiler
import com.experimental.compilation.statements.FunDeclarationCompiler
import com.experimental.compilation.statements.FunctionCallStatementCompiler
import com.experimental.compilation.statements.VarDeclarationCompiler
import com.experimental.model.MiniVM

class MiniVMBuilder {

    fun build(): MiniVM {
        val typeCompiler = TypeCompiler()
        val funCallCompiler = FunctionCallExpressionCompiler()
        val arithmeticCompiler = ArithmeticCompiler()
        val expressionCompilers = listOf(
            arithmeticCompiler,
            funCallCompiler,
            LiteralCompiler(),
            VariableCompiler(),
        )
        val expressionCompiler = ExpressionCompiler(expressionCompilers)
        arithmeticCompiler.expressionCompiler = expressionCompiler
        funCallCompiler.expressionCompiler = expressionCompiler
        val funDeclarationCompiler = FunDeclarationCompiler(null, expressionCompiler, typeCompiler)
        val statementCompilers = listOf(
            VarDeclarationCompiler(expressionCompiler, typeCompiler),
            funDeclarationCompiler,
            FunctionCallStatementCompiler(funCallCompiler)
        )
        val statementCompiler = StatementCompiler(statementCompilers)
        val multipleStatementsCompiler = MultipleStatementsCompiler(statementCompiler)
        funDeclarationCompiler.statementCompiler = multipleStatementsCompiler
        return MiniVMImpl(
            CompilerImpl(multipleStatementsCompiler)
        )
    }
}