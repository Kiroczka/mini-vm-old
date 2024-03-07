package com.experimental.exceptions

import com.experimental.compilation.PartType

class BuilderNotFoundException(type: PartType) :CompilationException("Builder with type: $type not found")