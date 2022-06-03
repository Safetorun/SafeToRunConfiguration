package com.safetorun.output

import com.safetorun.configuration.SafeToRunConfiguration
import kotlinx.serialization.json.Json


fun SafeToRunConfiguration.toJson(): String = Json.encodeToString(SafeToRunConfiguration.serializer(), this)