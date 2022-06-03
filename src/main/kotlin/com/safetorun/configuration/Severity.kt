package com.safetorun.configuration
/**
 * Severity
 */
@kotlinx.serialization.Serializable
enum class Severity {
    /**
     * Warn if fails
     */
    Warn,

    /**
     * Error if fails
     */
    Error,

    /**
     * Do nothing if fails
     */
    None
}
