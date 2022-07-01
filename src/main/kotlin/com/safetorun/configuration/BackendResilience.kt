package com.safetorun.configuration

import com.safetorun.resillience.*

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class BackendResilience(
    val blacklistedAppCheck: List<BlacklistedAppConfiguration> = emptyList(),
    val verifySignatureConfiguration: List<VerifySignatureConfiguration> = emptyList(),
    val installOriginCheck: List<InstallOriginCheck> = emptyList(),
    val osCheckConfiguration: List<OSCheckConfiguration> = emptyList()
)

/**
 * Backend resilience builder
 */
class BackendResilienceBuilder internal constructor() {
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

    internal fun build() = BackendResilience(
        blacklistedAppCheck,
        verifySignatureConfiguration,
        installOriginCheck,
        osCheckConfiguration
    )
}
