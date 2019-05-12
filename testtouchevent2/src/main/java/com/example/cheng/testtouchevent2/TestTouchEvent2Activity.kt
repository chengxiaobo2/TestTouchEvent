package com.example.cheng.testtouchevent2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.cheng.testtouchevent2.touch.TouchTarget
import com.example.cheng.testtouchevent2.util.loge
import kotlinx.android.synthetic.main.activity_test_touch_event2.*

/**
 * TestTouchEvent2Activity
 *
 * @author chengxiaobo
 * @time 2019/5/12 10:08
 */
class TestTouchEvent2Activity : AppCompatActivity() {

    val CANCEL = "CANCEL"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_touch_event2)

        val touchTargetA = TouchTarget("A")
        val touchTargetB = TouchTarget("B")
        val touchTargetC = TouchTarget("C")
        val touchTargetD = TouchTarget("D")
        val touchTargetE = TouchTarget("E")

        touchTargetA.next = touchTargetB
        touchTargetB.next = touchTargetC
        touchTargetC.next = touchTargetD
        touchTargetD.next = touchTargetE

        touchTargetA.touchView.isIntercept = false
        touchTargetB.touchView.isIntercept = false
        touchTargetC.touchView.isIntercept = true
        touchTargetD.touchView.isIntercept = false
        touchTargetE.touchView.isIntercept = false

        touchTargetA.touchView.isDealTouch = false
        touchTargetB.touchView.isDealTouch = true
        touchTargetC.touchView.isDealTouch = false
        touchTargetD.touchView.isDealTouch = false
        touchTargetE.touchView.isDealTouch = false

        btnClick.setOnClickListener {
            dispatchTouchEvent(touchTargetA)
            loge("===========dispatchTouchEvent  后输出 touchTarget==========")
            printTouchTarget(touchTargetA)
        }

    }

    /**
     * 模拟事件分发+拦截 很有意思，递归递归递归
     */
    private fun dispatchTouchEvent(touchTarget: TouchTarget): Boolean {
        if (touchTarget.touchView.intercepter()) {
            //1.TouchTarget的后续的TouchTarget都会调用TouchEvent(action="CANCEL")
            //2.并且TouchTarget以及TouchTarget后续的TouchTarget.next=null
            touchTarget.next?.let {
                cancelTargetNext(it)
                touchTarget.next = null
            }

        }
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

    /**
     * 1.TouchTarget的后续的TouchTarget都会调用TouchEvent(action="CANCEL")
     * 2.并且TouchTarget以及TouchTarget后续的TouchTarget.next=null
     * 又是递归，递归，递归。哈哈哈哈
     */
    private fun cancelTargetNext(touchTarget: TouchTarget) {
        while (touchTarget.next != null) {
            cancelTargetNext(touchTarget.next!!)
            touchTarget.next = null
            touchTarget.touchView.onTouchEvent(CANCEL)
            return
        }
        touchTarget.touchView.onTouchEvent(CANCEL)
    }

    private fun printTouchTarget(touchTarget: TouchTarget) {
        var currentTouchTarget: TouchTarget? = touchTarget
        while (currentTouchTarget != null) {
            var name = "null"
            if (currentTouchTarget.next != null) {
                name = currentTouchTarget.next!!.name
            }
            loge("target:${currentTouchTarget.name}  target.next: ${name}")
            currentTouchTarget = currentTouchTarget.next
        }

    }
}
