package com.experimental.compilation

import com.experimental.components.Expression

class ExpressionCompiler(concreteCompilers: List<ConcreteExpressionCompiler>) :
    ComponentCompiler<Expression>(concreteCompilers)