# [APP NAME] Integration Tests

## How to run
* Run from IDE (IntelliJ - recommended). Tests are located at `/src/integration-test/java/org.housecallpro.test`
* Run from command line `mvn test -Pintegration-tests` [NOT READY YET]

## Configuration
Configuration is designed in a way to be controlled by environment variables.

    [BROWSER, APPLICATION_URL]

##### Default:
* Browser: `Chrome (without headless)`
* Application URL: `https://pro.housecallpro.com/pro/log_in`

#### Supported browsers:
* `CHROME`
* `CHROME_HEADLESS`
* `CHROME_IN_DOCKER`