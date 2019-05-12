package com.example.cheng.testtouchevent1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.cheng.testtouchevent1.touch.TouchTarget
import kotlinx.android.synthetic.main.activity_test_touch_event1.*

/**
 *
 * @author chengxiaobo
 * @time 2019/5/11 21:10
 */
class TestTouchEvent1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_touch_event1)
        val touchTargetA = TouchTarget("A")
        val touchTargetB = TouchTarget("B")
        val touchTargetC = TouchTarget("C")
        val touchTargetD = TouchTarget("D")

        touchTargetA.next = touchTargetB
        touchTargetB.next = touchTargetC
        touchTargetC.next = touchTargetD

        touchTargetA.touchView.isDealTouch = false
        touchTargetB.touchView.isDealTouch = true
        touchTargetC.touchView.isDealTouch = false
        touchTargetD.touchView.isDealTouch = false

        btnClick.setOnClickListener {
            dispatchTouchEvent(touchTargetA)
        }

    }

    /**
     * 模拟事件分发-很有意思，递归递归递归
     */
    private fun dispatchTouchEvent(touchTarget: TouchTarget): Boolean {
        val next = touchTarget.next
        return if (next == null) {
            touchTarget.touchView.onTouchEvent()
        } else {
            val isDeal = dispatchTouchEvent(touchTarget.next!!)
            if (isDeal) {
                true
            } else {
                touchTarget.touchView.onTouchEvent()
            }
        }
    }
}
