import 'dart:async';

import 'package:flutter/services.dart';

class Umeng {
  static const MethodChannel _channel =
  const MethodChannel('im.weshine.plugin/umeng');

  static Future<String> initUmeng(Map map) async {
    final String version = await _channel.invokeMethod('initUmeng', map);
    return version;
  }
}
