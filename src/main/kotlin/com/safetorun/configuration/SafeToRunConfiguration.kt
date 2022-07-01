package com.safetorun.configuration

import com.safetorun.output.toJson
import com.safetorun.resillience.Severity

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class SafeToRunConfiguration internal constructor(
    val backendResilience: BackendResilience
)

class SafeToRunConfigurationBuilder internal constructor() {
    var backendResilience : BackendResilience = BackendResilience()

    fun backendResilience(builder: BackendResilienceBuilder.() -> Unit) {
        backendResilience = BackendResilienceBuilder()
            .apply(builder)
            .build()
    }

    internal fun build() = SafeToRunConfiguration(backendResilience)
}

fun safeToRun(builder: SafeToRunConfigurationBuilder.() -> Unit): SafeToRunConfiguration =
   SafeToRunConfigurationBuilder()
       .apply(builder)
       .build()

fun main() {
    safeToRun {
        backendResilience {
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
    }.let {
        println(it.toJson())
    }
}