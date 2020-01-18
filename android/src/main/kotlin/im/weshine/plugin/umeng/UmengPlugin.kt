package im.weshine.plugin.umeng

import android.app.Activity
import android.util.Log
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class UmengPlugin(activity: Activity) : MethodCallHandler {
    private val mActivity: Activity = activity

    companion object {
        private var channel: MethodChannel? = null
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            channel = MethodChannel(registrar.messenger(), "im.weshine.plugin/umeng")
            channel?.setMethodCallHandler(UmengPlugin(registrar.activity()))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "initUmeng") {
            val appKey = call.argument<String>("appKey")
            val channel = call.argument<String>("channel")
            val pushKey = call.argument<String>("pushKey")
            Log.e("asda", pushKey)
            Log.e("asda", channel)
            Log.e("asda", appKey)
            initUmeng(appKey!!, channel!!, pushKey!!, result)
        } else {
            result.notImplemented()
        }
    }

    /**
     * 初始化
     */
    private fun initUmeng(appKey: String, channel: String, pushKey: String, result: Result) {
        UMConfigure.init(mActivity, appKey, channel, UMConfigure.DEVICE_TYPE_PHONE, pushKey)
        val agent = PushAgent.getInstance(mActivity)
        agent.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String?) {
                mActivity.runOnUiThread {
                    Log.e("umeng", "deviceToken ---> $deviceToken")
                    result.success("Umeng Plugin:Init success")
                }
            }

            override fun onFailure(s: String?, s1: String?) {
                Log.e("umeng", "s:$s,  s1:$s1")
                mActivity.runOnUiThread {
                    result.success("Umeng Plugin:Init fail")
                }
            }
        })
    }
}
