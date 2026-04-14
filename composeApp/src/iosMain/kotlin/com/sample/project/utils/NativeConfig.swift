class NativeConfig {

    static func getBaseUrl() -> String {
        return ios_getBaseUrl()
    }

    static func getApiKey() -> String {
        return ios_getApiKey()
    }

    static func getClientId() -> String {
        return ios_getClientId()
    }

    static func getSecretKey() -> String {
        return ios_getSecretKey()
    }

    static func getAnotherKey() -> String {
        return ios_getAnotherKey()
    }
}