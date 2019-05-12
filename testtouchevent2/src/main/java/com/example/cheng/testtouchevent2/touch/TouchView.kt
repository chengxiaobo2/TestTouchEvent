package com.example.cheng.testtouchevent2.touch

import com.example.cheng.testtouchevent2.util.loge


/**
 * TouchView
 *
 * @author chengxiaobo
 * @time 2019/5/12 10:04
 */
class TouchView(val name: String) {

    //是否处理touch事件
    var isDealTouch = false
    var isIntercept = false

    fun onTouchEvent(actionName: String = "Move"): Boolean {
        loge(" $name  actionName: $actionName  isIntercept  $isIntercept  isDealTouch  $isDealTouch")
        return isDealTouch
    }

    fun intercepter(): Boolean {
        return isIntercept
    }
}