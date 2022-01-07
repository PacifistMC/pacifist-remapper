package io.github.rancraftplayz.remapper;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

public class RemapperPlugin implements Plugin<Project> {
    static SpigotMappingExtension extension;
    static File mappingsDir;

    @Override
    public void apply(Project project) {
        extension = project.getExtensions().create("spigot", SpigotMappingExtension.class);
        mappingsDir = new File(project.getRootDir(), ".gradle" + File.separator + "mappings");

        project.getConfigurations().create("remapLib");

        project.getConfigurations().create("accessWidener");
        project.getConfigurations().create("accessWidenerLib");

        project.getTasks().create("remapJar", RemapJarTask.class);
        project.getTasks().create("applyAccessWidener", ApplyAccessWidenerTask.class);
    }
}
