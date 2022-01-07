Make an issue if you are having troubles!
___
## Applying the plugin
#### Maybe take a look at [IgniteModTemplate](https://github.com/PacifistMC/IgniteModTemplate)
Thanks to Hidin for helping me publish this plugin!
#### Groovy
Using the [plugins DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block):

```groovy
plugins {
  id "io.github.rancraftplayz.remapper" version "0.2"
}
```

Using the [legacy plugin application](https://docs.gradle.org/current/userguide/plugins.html#sec:old_plugin_application):
<details><summary>Click to View</summary>

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "io.github.rancraftplayz.remapper:pacifist-remapper:0.2"
  }
}

apply plugin: "io.github.rancraftplayz.remapper"
```
</details>

#### Kotlin

Using the [plugins DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block):

```kotlin
plugins {
  id("io.github.rancraftplayz.remapper") version "0.2"
}
```

Using the [legacy plugin application](https://docs.gradle.org/current/userguide/plugins.html#sec:old_plugin_application):
<details><summary>Click to View</summary>

```kotlin
buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("io.github.rancraftplayz.remapper:pacifist-remapper:0.2")
  }
}

apply(plugin = "io.github.rancraftplayz.remapper")
```
</details>

---
## How to use
```groovy
dependencies {
    remapLib "org.spigotmc:spigot:1.17.1-R0.1-SNAPSHOT:remapped-mojang"
}

spigot {
  version = "1.17.1"
}
```
Remember to change the version numbers

The **remapLib** is where it'll remap from

To remap the jar you'll need to run the task **remapJar**
You can make this task depend on [shadowJar](https://github.com/PacifistMC/pacifist-remapper#shadowjar-configuration-required-for-now) (or something simillar) to automaticly create & remap the jar
___
## Access Wideners
We support three namespaces (mojang, spigot, official)
```groovy
dependencies {
    // Note that we don't support multiple access wideners yet
    accessWidener fileTree(dir: 'src/main/resources', include: ['*.accesswidener'])
    accessWidenerLib "org.spigotmc:spigot:1.17.1-R0.1-SNAPSHOT:remapped-mojang"
}
```
The **accessWidener** configuration is the path .accesswidener file

The **accessWidenerLib** is where will it apply the access wideners

To actually apply the accsss wideners you need to run the task **applyAccessWidener**

##### ignite.mod.json
```json
"access_widener": [
    "example-obf.accesswidener"
]
  ```
  The **-obf** must be added after the name of the access widener because the actual example.accesswidener is not an access widener that Ignite understands! (unless if you're making it in spigot mappings)
  ___
  ## ShadowJar configuration (Required for now)
  If you're using shadow or something similar then you'll need to set the path & name to where the original jar task makes its jar
  If you're using default build stuff then you can add this to your shadowJar
  ```groovy
  shadowJar {
    archiveBaseName.set("${project.name}-${project.version}")
    archiveClassifier.set('')
    archiveVersion.set('')
  }
  ```
  ___
  If you wanna see a full project using this then you can look at [PacifistOptimizations](https://github.com/PacifistMC/PacifistOptimizations)
