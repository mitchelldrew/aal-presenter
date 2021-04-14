package com.aal.presenter

expect class Freezer(): IFreezer {
    override fun freeze(obj:Any):Any
}