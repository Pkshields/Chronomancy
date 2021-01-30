package dev.paulshields.chronomancy.config.service

import dev.paulshields.chronomancy.common.KeyNotFoundException
import dev.paulshields.chronomancy.common.readSystemVariable

class EnvironmentConfigurationStore(private val propertyFileParser: PropertyFileParser) {
    private var propertiesFromFile = emptyMap<String, String>()

    fun loadFile(backupPropertiesFileLocation: String) {
        propertiesFromFile = propertyFileParser.parse(backupPropertiesFileLocation)
    }

    fun getPropertyOrNull(key: String) = readSystemVariable(key) ?: propertiesFromFile[key]

    fun getProperty(key: String) = getPropertyOrNull(key) ?: throw KeyNotFoundException(key)
}
