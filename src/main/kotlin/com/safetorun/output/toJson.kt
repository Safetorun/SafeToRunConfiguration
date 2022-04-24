package com.safetorun.output

import com.google.gson.Gson
import com.safetorun.configuration.SafeToRunConfiguration

private val gson = Gson()

fun SafeToRunConfiguration.toJson(): String = gson.toJson(this)