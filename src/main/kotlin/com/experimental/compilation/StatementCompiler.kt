package com.experimental.compilation

import com.experimental.components.Statement

class StatementCompiler(concreteCompilers: List<ConcreteStatementCompiler>) :
    ComponentCompiler<Statement>(concreteCompilers)