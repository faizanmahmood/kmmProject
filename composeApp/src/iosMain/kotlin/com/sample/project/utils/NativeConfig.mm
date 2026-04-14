#import <Foundation/Foundation.h>

extern const char* getBaseUrl();
extern const char* getApiKey();
extern const char* getClientId();
extern const char* getSecretKey();
extern const char* getAnotherKey();

NSString* ios_getBaseUrl() {
    return [NSString stringWithUTF8String:getBaseUrl()];
}

NSString* ios_getApiKey() {
    return [NSString stringWithUTF8String:getApiKey()];
}

NSString* ios_getClientId() {
    return [NSString stringWithUTF8String:getClientId()];
}

NSString* ios_getSecretKey() {
    return [NSString stringWithUTF8String:getSecretKey()];
}

NSString* ios_getAnotherKey() {
    return [NSString stringWithUTF8String:getAnotherKey()];
}