Make an issue if you are having troubles!
We won't bite you for making useless issues
## How to download this?
Well uhm I don't really know how to publish a gradle plugin/dependency (psst someone publish this for me and then make an issue letting me know)
So for now you'll have to be scuffed just like me!

You'll first need to [download](https://github.com/PacifistMC/pacifist-remapper/releases) this
then in your peject make a folder named libs and paste the jar file in that
and then add this to your build.gradle file without breaking the entire universe
```groovy
import me.rancraftplayz.pacifist.remapper.RemapperPlugin

buildscript {
    dependencies{
        classpath files('libs/pacifist-remapper.jar')
    }
}
apply plugin: RemapperPlugin
```
If something goes wrong then you can look at my project [PacifistOptimizations's build.gradle file](https://github.com/PacifistMC/PacifistOptimizations/blob/2664c715836988f68787b30e95d11ada29dc7400/build.gradle)

## How to use
```groovy
dependencies {
    mojangProguardMappings "org.spigotmc:minecraft-server:1.17.1-R0.1-SNAPSHOT:maps-mojang@txt"
    spigotCsrgMappings "org.spigotmc:minecraft-server:1.17.1-R0.1-SNAPSHOT:maps-spigot@csrg"
    remapLib "org.spigotmc:spigot:1.17.1-R0.1-SNAPSHOT:remapped-mojang"
}
```
The **mojangProguardMappings** is the mojang mappings
The **spigotCsrgMappings** is the spigot mappings
The **remapLib** is where it'll remap from

## Access Wideners
```groovy
dependencies {
    // Note that we don't support multiple access wideners yet
    accessWidener fileTree(dir: 'src/main/resources', include: ['*.accesswidener'])
    accessWidenerLib "org.spigotmc:spigot:1.17.1-R0.1-SNAPSHOT:remapped-mojang"
}
```
The **accessWidener** configuration is the path .accesswidener file
The **accessWidenerLib** is where will it apply the access wieners

To actually apply the accsss wideners you need to run the task **applyAccessWidener**

##### ignite.mod.json
```json
"access_widener": [
    "example-obf.accesswidener"
]
  ```
  The **-obf** must be added after the name of the access widener because the actual example.accesswidener is not an access widener that Ignite understands! (unless if you're making it in spigot mappings)
  
  ## ShadowJar configuration (Required for now)
  If you're using shadow or something simillar then you'll need to set the path to where the original jar task makes it's jar
  If you're using default build stuff then you can add this to your shadowJar
  ```groovy
  shadowJar {
    archiveBaseName.set("${project.name}-${project.version}")
    archiveClassifier.set('')
    archiveVersion.set('')
  }
  ```
  
  # 
  # 
  If you wanna see a full project using this you can look at [PacifistOptimizations](https://github.com/PacifistMC/PacifistOptimizations)
