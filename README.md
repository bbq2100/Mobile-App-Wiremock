# Mobile-App-Wiremock

## How to start the sample code?
1. Import the sample Android project in AndroidStudio.
2. Start Wiremock. For further documentation see the `wiremock` directory.
3. That's all, folks ;-)

## Directory description

### Android
Contains a gradle based Android project. Generally, the recommended IDE for Android development is AndroidStudio. See https://developer.android.com/sdk/index.html.

### Wiremock
Contains altogether with the `standalone` Wiremock binary, the demonstrated mocking/stubbing configurations. See `wiremock/mappings`.

### Backend
Holds the Spring Boot server application, which was required for the Wiremock Proxy example. The application can be started via `java -jar -Dserver.port=X backend.jar`. Notice: the port 8080 is already occupied by the default Wiremock HTTP port.
