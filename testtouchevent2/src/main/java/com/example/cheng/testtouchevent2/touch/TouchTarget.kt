package com.example.cheng.testtouchevent2.touch

/**
 * TouchTarget
 *
 * @author chengxiaobo
 * @time 2019/5/12 10:04
 */
class TouchTarget(val name: String) {

    var next: TouchTarget? = null
    val touchView = TouchView(name)
}