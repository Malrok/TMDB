package com.moventes.moventest.tmdb.tools

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.moventes.moventest.tmdb.models.Configuration
import java.lang.reflect.Type

class ConfigurationDeserializer : JsonDeserializer<Configuration> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Configuration {
        val images = json?.asJsonObject?.get("images")

        return Gson().fromJson(images, typeOfT)
    }

}