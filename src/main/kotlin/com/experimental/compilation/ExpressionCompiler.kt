package com.experimental.compilation

import com.experimental.compilation.factories.BuildersFactory
import com.experimental.compilation.factories.CompilersFactory

class ExpressionCompiler(compilersFactory: CompilersFactory, buildersFactory: BuildersFactory) :
    CompilerCoordinator(compilersFactory, buildersFactory) {
    override fun getType(): PartType = GeneralSyntaxElement.EXPRESSION
}