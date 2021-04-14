package com.aal.presenter

import kotlin.native.concurrent.freeze

actual class Freezer: IFreezer {
    actual override fun freeze(obj: Any): Any {
        obj.freeze()
        return obj
    }
}