package com.experimental.impl

import com.experimental.compilation.ExpressionCompiler
import com.experimental.compilation.ProgramCompiler
import com.experimental.compilation.StatementCompiler
import com.experimental.compilation.TypeCompiler
import com.experimental.compilation.expressions.ArithmeticExpCompiler
import com.experimental.compilation.expressions.FunCallExpCompiler
import com.experimental.compilation.expressions.LiteralExpCompiler
import com.experimental.compilation.expressions.OperatorExpCompiler
import com.experimental.compilation.expressions.VarExpCompiler
import com.experimental.compilation.expressions.builders.ArithmeticExpBuilder
import com.experimental.compilation.expressions.builders.FunCallArgsBuilder
import com.experimental.compilation.expressions.builders.FunCallExpBuilder
import com.experimental.compilation.expressions.builders.VarExpBuilder
import com.experimental.compilation.factories.BuildersFactory
import com.experimental.compilation.factories.CompilersFactory
import com.experimental.compilation.statements.CommentCompiler
import com.experimental.compilation.statements.FunArgsCompiler
import com.experimental.compilation.statements.FunCallArgsCompiler
import com.experimental.compilation.statements.FunDeclarationCompiler
import com.experimental.compilation.statements.FunNameCompiler
import com.experimental.compilation.statements.VarDeclarationCompiler
import com.experimental.compilation.statements.VarInitializationCompiler
import com.experimental.compilation.statements.VarNameCompiler
import com.experimental.compilation.statements.builders.FunArgsBuilder
import com.experimental.compilation.statements.builders.FunDeclarationStBuilder
import com.experimental.compilation.statements.builders.VarDeclarationStBuilder
import com.experimental.compilation.statements.builders.VarInitStBuilder
import com.experimental.model.MiniVM

class MiniVMBuilder {

    fun build(): MiniVM {
        val programCompiler = buildCompiler()
        return MiniVMImpl(programCompiler)
    }

    fun buildCompiler(): ProgramCompiler {
        val expressionBuildersFactory = BuildersFactory(
            listOf(
                ArithmeticExpBuilder(),
                FunCallExpBuilder(),
                VarExpBuilder(),
                FunCallArgsBuilder(),
            )
        )
        val expressionCompilersFactory = CompilersFactory(
            listOf(
                FunCallExpCompiler(),
                ArithmeticExpCompiler(),
                LiteralExpCompiler(),
                VarExpCompiler()
            ), listOf(
                OperatorExpCompiler(),
                VarNameCompiler(),
                FunNameCompiler(),
                FunCallArgsCompiler()

            )
        )
        val expressionCompiler = ExpressionCompiler(expressionCompilersFactory, expressionBuildersFactory)
        val statementBuildersFactory = BuildersFactory(
            listOf(
                VarInitStBuilder(),
                VarDeclarationStBuilder(),
                FunArgsBuilder(),
                FunDeclarationStBuilder()
            )
        )
        val statementCompilersFactory = CompilersFactory(
            listOf(
                CommentCompiler(),
                VarInitializationCompiler(),
                FunDeclarationCompiler(),
                expressionCompiler
            ),
            listOf(
                expressionCompiler,
                FunNameCompiler(),
                FunArgsCompiler(),
                VarDeclarationCompiler(),
                VarNameCompiler(),
                TypeCompiler(),
            )

        )
        val statementCompiler = StatementCompiler(statementCompilersFactory, statementBuildersFactory)
        val programCompiler = ProgramCompiler(statementCompiler)
        return programCompiler
    }
}