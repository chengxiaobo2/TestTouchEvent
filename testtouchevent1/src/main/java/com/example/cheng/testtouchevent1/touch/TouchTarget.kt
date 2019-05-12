package com.example.cheng.testtouchevent1.touch

/**
 * TouchTarget
 *
 * @author chengxiaobo
 * @time 2019/5/11 21:20
 */
class TouchTarget(val name: String) {

    var next: TouchTarget? = null
    val touchView = TouchView(name)
}