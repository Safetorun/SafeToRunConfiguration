package com.safetorun.output

import com.safetorun.configuration.BackendResilience
import com.safetorun.configuration.SafeToRunConfiguration
import kotlinx.serialization.json.Json


fun BackendResilience.toJson(): String = Json.encodeToString(BackendResilience.serializer(), this)
fun SafeToRunConfiguration.toJson(): String = Json.encodeToString(SafeToRunConfiguration.serializer(), this)