package com.wcaokaze.ko5hian.runtimefile

import java.io.*

internal interface RuntimeFileGenerator {
   fun generate(packageDir: File)
}
