package fr.euphyllia.fidorial.gradle

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.SetProperty
import org.gradle.kotlin.dsl.setProperty
import javax.inject.Inject
import kotlin.collections.emptySet

abstract class FidorialBuildExtension @Inject constructor(objects: ObjectFactory) {
    /**
     * The modules that are allowed to read unnamed modules.
     */
    val readUnnamedModules: SetProperty<String> = objects.setProperty<String>().convention(emptySet())
}
