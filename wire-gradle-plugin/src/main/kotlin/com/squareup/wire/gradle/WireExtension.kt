/*
 * Copyright (C) 2019 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire.gradle

import com.squareup.wire.schema.EventListener
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.file.SourceDirectorySet
import org.gradle.api.internal.catalog.DelegatingProjectDependency
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderConvertible
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.util.PatternFilterable

open class WireExtension(project: Project) {
  private val objectFactory = project.objects

  internal val sourcePaths = mutableSetOf<String>()
  internal val protoPaths = mutableSetOf<String>()
  internal val sourceTrees = mutableSetOf<SourceDirectorySet>()
  internal val protoTrees = mutableSetOf<SourceDirectorySet>()
  internal val sourceJars = mutableSetOf<ProtoRootSet>()
  internal val protoJars = mutableSetOf<ProtoRootSet>()
  internal val roots = mutableSetOf<String>()
  internal val prunes = mutableSetOf<String>()
  internal val moves = mutableListOf<Move>()
  internal val opaques = mutableSetOf<String>()
  internal val eventListenerFactories = mutableSetOf<EventListener.Factory>()
  internal val eventListenerFactoryClasses = mutableSetOf<String>()
  internal var onlyVersion: String? = null
  internal var sinceVersion: String? = null
  internal var untilVersion: String? = null
  internal var permitPackageCycles: Boolean = false

  @Input
  @Optional
  fun roots() = roots.toSet()

  /**
   * See [com.squareup.wire.schema.WireRun.treeShakingRoots]
   */
  fun root(vararg roots: String) {
    this.roots.addAll(roots)
  }

  @Input
  @Optional
  fun prunes() = prunes.toSet()

  /**
   * See [com.squareup.wire.schema.WireRun.treeShakingRubbish]
   */
  fun prune(vararg prunes: String) {
    this.prunes.addAll(prunes)
  }

  @Input
  @Optional
  fun sinceVersion() = sinceVersion

  /**
   * See [com.squareup.wire.schema.WireRun.sinceVersion]
   */
  fun sinceVersion(sinceVersion: String) {
    this.sinceVersion = sinceVersion
  }

  @Input
  @Optional
  fun untilVersion() = untilVersion

  /**
   * See [com.squareup.wire.schema.WireRun.untilVersion]
   */
  fun untilVersion(untilVersion: String) {
    this.untilVersion = untilVersion
  }

  @Input
  @Optional
  fun onlyVersion() = onlyVersion

  /**
   * See [com.squareup.wire.schema.WireRun.onlyVersion].
   */
  fun onlyVersion(onlyVersion: String) {
    this.onlyVersion = onlyVersion
  }

  @Input
  fun permitPackageCycles() = permitPackageCycles

  /**
   * See [com.squareup.wire.schema.WireRun.permitPackageCycles]
   */
  fun permitPackageCycles(permitPackageCycles: Boolean) {
    this.permitPackageCycles = permitPackageCycles
  }

  /**
   * A user-provided file listing [roots] and [prunes]
   */
  @get:Input
  @get:Optional
  var rules: String? = null

  /** Specified what types to output where. Maps to [com.squareup.wire.schema.Target] */
  @get:Input
  val outputs = mutableListOf<WireOutput>()

  /**
   * True to emit `.proto` files into the output resources. Use this when your `.jar` file can be
   * used as a library for other proto or Wire projects.
   *
   * Note that only the `.proto` files used in the library will be included, and these files will
   * have tree-shaking applied.
   */
  @get:Input
  @get:Optional
  var protoLibrary = false

  /**
   * If true, Wire will fail if not all [roots] and [prunes] are used when tree-shaking the schema.
   * This can help discover incorrect configurations early and avoid misexpectations about the
   * built schema.
   * See [treeShakingRoots][com.squareup.wire.schema.WireRun.treeShakingRoots] and
   * [treeShakingRubbish][com.squareup.wire.schema.WireRun.treeShakingRubbish].
   *
   * If false, unused [roots] and [prunes] will be printed as warnings.
   */
  @get:Input
  @get:Optional
  var rejectUnusedRootsOrPrunes = false

  /**
   * True to not write generated types to disk, but emit the names of the source files that would
   * otherwise be generated.
   */
  @get:Input
  @get:Optional
  var dryRun = false

  @InputFiles
  @Optional
  fun getSourcePaths() = sourcePaths.toSet()

  @InputFiles
  @Optional
  fun getSourceTrees() = sourceTrees.toSet()

  @InputFiles
  @Optional
  fun getSourceJars() = sourceJars.toSet()

  /**
   * Source paths for local jars and directories, as well as remote binary dependencies
   */
  // TODO(Benoit) Delete this because it seems unused? I think the DSL only pass down ProtoRootSet.
  fun sourcePath(vararg sourcePaths: String) {
    this.sourcePaths.addAll(sourcePaths)
  }

  /**
   * Source paths for local file trees, backed by a [org.gradle.api.file.SourceDirectorySet]
   * Must provide at least a [org.gradle.api.file.SourceDirectorySet.srcDir]
   */
  fun sourcePath(action: Action<ProtoRootSet>) {
    populateRootSets(action, sourceTrees, sourceJars, "source-tree")
  }

  @Input
  fun eventListenerFactories() = eventListenerFactories.toSet()

  /** Add a [EventListener.Factory]. */
  fun eventListenerFactory(eventListenerFactory: EventListener.Factory) {
    this.eventListenerFactories.add(eventListenerFactory)
  }

  @Input
  fun eventListenerFactoryClasses() = eventListenerFactoryClasses.toSet()

  /** Add a [EventListener.Factory] by name. The referred class must have a no-arguments constructor. */
  fun eventListenerFactoryClass(eventListenerFactoryClass: String) {
    this.eventListenerFactoryClasses.add(eventListenerFactoryClass)
  }

  @InputFiles
  @Optional
  fun getProtoPaths(): Set<String> {
    return protoPaths
  }

  @InputFiles
  @Optional
  fun getProtoTrees(): Set<SourceDirectorySet> {
    return protoTrees
  }

  @InputFiles
  @Optional
  fun getProtoJars(): Set<ProtoRootSet> {
    return protoJars
  }

  /**
   * Proto paths for local jars and directories, as well as remote binary dependencies
   */
  fun protoPath(vararg protoPaths: String) {
    this.protoPaths.addAll(protoPaths)
  }

  /**
   * Proto paths for local file trees, backed by a [org.gradle.api.file.SourceDirectorySet]
   * Must provide at least a [org.gradle.api.file.SourceDirectorySet.srcDir]
   */
  fun protoPath(action: Action<ProtoRootSet>) {
    populateRootSets(action, protoTrees, protoJars, "proto-tree")
  }

  private fun populateRootSets(
    action: Action<ProtoRootSet>,
    sourceTrees: MutableSet<SourceDirectorySet>,
    sourceJars: MutableSet<ProtoRootSet>,
    name: String,
  ) {
    val protoRootSet = objectFactory.newInstance(ProtoRootSet::class.java)
    action.execute(protoRootSet)

    protoRootSet.validate()

    val hasSrcDirs = protoRootSet.srcDirs.isNotEmpty()
    val hasSrcJar = protoRootSet.srcJar != null
    val hasSrcJarAsExternalModuleDependency = protoRootSet.srcJarAsExternalModuleDependency != null
    val hasSrcProjectDependency = protoRootSet.srcProjectDependency != null
    val hasSrcProject = protoRootSet.srcProject != null

    if (hasSrcDirs) {
      // map to SourceDirectorySet which does the work for us!
      val protoTree = objectFactory
        .sourceDirectorySet(name, "Wire proto sources for $name.")
        .srcDirs(protoRootSet.srcDirs)

      protoRootSet.filters
        .ifEmpty { listOf(Include("**/*.proto")) }
        .forEach { it.act(protoTree.filter) }

      sourceTrees.add(protoTree)
    }

    if (hasSrcJar || hasSrcJarAsExternalModuleDependency || hasSrcProject || hasSrcProjectDependency) {
      sourceJars.add(protoRootSet)
    }
  }

  fun java(action: Action<JavaOutput>) {
    val javaOutput = objectFactory.newInstance(JavaOutput::class.java)
    action.execute(javaOutput)
    outputs += javaOutput
  }

  fun kotlin(action: Action<KotlinOutput>) {
    val kotlinOutput = objectFactory.newInstance(KotlinOutput::class.java)
    action.execute(kotlinOutput)
    outputs += kotlinOutput
  }

  fun proto(action: Action<ProtoOutput>) {
    val protoOutput = objectFactory.newInstance(ProtoOutput::class.java)
    action.execute(protoOutput)
    outputs += protoOutput
  }

  fun custom(action: Action<CustomOutput>) {
    val customOutput = objectFactory.newInstance(CustomOutput::class.java)
    action.execute(customOutput)
    outputs += customOutput
  }

  @Input
  @Optional
  fun moves() = moves.toList()

  fun move(action: Action<Move>) {
    val move = objectFactory.newInstance(Move::class.java)
    action.execute(move)
    moves += move
  }

  @Input
  @Optional
  fun opaques() = opaques.toSet()

  fun opaque(vararg opaques: String) {
    this.opaques.addAll(opaques)
  }

  // TODO(Benoit) See how we can make this class better, it's a mess and doesn't scale nicely.
  open class ProtoRootSet {
    val srcDirs = mutableListOf<String>()
    var srcJar: String? = null
    var srcProject: String? = null
    var srcProjectDependency: DelegatingProjectDependency? = null
    var srcJarAsExternalModuleDependency: Provider<MinimalExternalModuleDependency>? = null
    val includes = mutableListOf<String>()
    val excludes = mutableListOf<String>()

    internal val filters: Collection<Filter>
      get() = includes.map(::Include) + excludes.map(::Exclude)

    fun srcDir(dir: String) {
      srcDirs += dir
    }

    fun srcDirs(vararg dirs: String) {
      srcDirs += dirs
    }

    fun srcJar(jar: String) {
      srcJar = jar
    }

    fun srcJar(provider: Provider<MinimalExternalModuleDependency>) {
      srcJarAsExternalModuleDependency = provider
    }

    fun srcJar(convertible: ProviderConvertible<MinimalExternalModuleDependency>) {
      srcJar(convertible.asProvider())
    }

    fun srcProject(projectPath: String) {
      srcProject = projectPath
    }

    fun srcProject(project: DelegatingProjectDependency) {
      srcProjectDependency = project
    }

    fun include(vararg includePaths: String) {
      includes += includePaths
    }

    fun exclude(vararg excludePaths: String) {
      excludes += excludePaths
    }
  }

  internal sealed class Filter(val glob: String) {
    abstract fun act(filter: PatternFilterable)
  }

  internal class Exclude(glob: String) : Filter(glob) {
    override fun act(filter: PatternFilterable) {
      filter.exclude(glob)
    }
  }

  internal class Include(glob: String) : Filter(glob) {
    override fun act(filter: PatternFilterable) {
      filter.include(glob)
    }
  }
}

private fun WireExtension.ProtoRootSet.validate() {
  val sources = listOf(
    srcDirs.isNotEmpty(),
    srcJar != null,
    srcJarAsExternalModuleDependency != null,
    srcProjectDependency != null,
    srcProject != null,
  )

  check(sources.count { it } <= 1) {
    "Only one source can be set among srcDirs, srcJar, and srcProject within one sourcePath or protoPath closure."
  }
}
