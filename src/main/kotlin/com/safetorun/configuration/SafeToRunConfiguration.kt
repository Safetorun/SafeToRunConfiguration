package com.safetorun.configuration

import com.safetorun.output.toJson

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class SafeToRunConfiguration(
    val blacklistedAppCheck: List<BlacklistedAppConfiguration>,
    val verifySignatureConfiguration: List<VerifySignatureConfiguration>,
    val installOriginCheck: List<InstallOriginCheck>,
    val osCheckConfiguration: List<OSCheckConfiguration>
)

class SafeToRunConfigurationBuilder internal constructor() {
    private val blacklistedAppCheck = mutableListOf<BlacklistedAppConfiguration>()
    private val verifySignatureConfiguration = mutableListOf<VerifySignatureConfiguration>()
    private val installOriginCheck = mutableListOf<InstallOriginCheck>()
    private val osCheckConfiguration = mutableListOf<OSCheckConfiguration>()

    fun verifySignature(severity: Severity, configuration: VerifySignatureConfigurationBuilder.() -> Unit) {
        verifySignatureConfiguration.add(
            VerifySignatureConfigurationBuilder(severity)
                .apply(configuration)
                .build()
        )
    }

    fun installOriginCheck(severity: Severity, installOrigin: InstallOriginBuilder.() -> Unit) {
        installOriginCheck.add(
            InstallOriginBuilder(severity).apply(installOrigin).build()
        )
    }

    fun oSCheck(osCheckConf: OSCheckConfigurationBuilder.() -> Unit) {
        osCheckConfiguration.add(
            OSCheckConfigurationBuilder().apply(osCheckConf).build()
        )
    }

    fun blacklistedApp(severity: Severity, blacklistedApp: BlacklistedAppConfigurationBuilder.() -> Unit) {
        blacklistedAppCheck.add(
            BlacklistedAppConfigurationBuilder(severity).apply(blacklistedApp).build()
        )
    }

    internal fun build() = SafeToRunConfiguration(
        blacklistedAppCheck,
        verifySignatureConfiguration,
        installOriginCheck,
        osCheckConfiguration
    )
}

fun safeToRun(builder: SafeToRunConfigurationBuilder.() -> Unit): SafeToRunConfiguration =
    SafeToRunConfigurationBuilder()
        .apply(builder)
        .build()


fun main() {
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
    }.let {
        println(it.toJson())
    }
}