# Safe to run configuration 

Example usage:

```kotlin
    safeToRun {
        installOriginCheck(Severity.Warn) {
            +"InstallOrigin"
        }
        verifySignature(Severity.Error) {
            +"signature1234"
        }

        blacklistedApp(Severity.Warn) {
            +"com.this.app"
        }

        oSCheck {
            add {
                allChecks = listOf()
            }
        }
    }
```
