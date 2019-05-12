package com.example.cheng.testtouchevent1.touch

import com.example.cheng.testtouchevent1.util.loge

/**
 * TouchView
 *
 * @author chengxiaobo
 * @time 2019/5/11 21:22
 */
class TouchView(val name: String) {

    //是否处理touch事件
    var isDealTouch = false

    fun onTouchEvent(): Boolean {
        loge("$name  isDealTouch  $isDealTouch")
        return isDealTouch
    }
}