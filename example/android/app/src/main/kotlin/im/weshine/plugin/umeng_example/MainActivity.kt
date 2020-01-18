package im.weshine.plugin.umeng_example

import android.os.Bundle
import com.umeng.message.PushAgent

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)
  }


  override fun onResume() {
    super.onResume()
    PushAgent.getInstance(this).onAppStart()
  }
}
