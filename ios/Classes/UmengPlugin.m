#import "UmengPlugin.h"
#import <umeng/umeng-Swift.h>

@implementation UmengPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftUmengPlugin registerWithRegistrar:registrar];
}
@end
