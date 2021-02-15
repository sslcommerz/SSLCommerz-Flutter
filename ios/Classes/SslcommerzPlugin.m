#import "SslcommerzPlugin.h"
#if __has_include(<sslcommerz/sslcommerz-Swift.h>)
#import <sslcommerz/sslcommerz-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "sslcommerz-Swift.h"
#endif

@implementation SslcommerzPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSslcommerzPlugin registerWithRegistrar:registrar];
}
@end
