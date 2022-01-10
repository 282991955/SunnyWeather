package com.sunnyweather.android.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


/**
 * @author cw
 * @date 2022-01-10 13:31
 */
abstract class BaseActivity : AppCompatActivity() {

    private fun setScreenRotate(screenRotate: Boolean) {
        requestedOrientation = if (screenRotate) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //设置竖屏模式
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setScreenRotate(false)
    }

    // 简化直接跳转
    inline fun <reified T> startActivity(context: Context) {
        val intent = Intent(context, T::class.java)
        startActivity(intent)
    }

    // 带数据跳转
    inline fun <reified T> startActivity(block: Intent.() -> Unit) {
        val intent = Intent(this, T::class.java)
        intent.block()
        startActivity(intent)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (isShouldHideInput(view, ev)) {
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    //判断是否隐藏键盘
    open fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }
}
