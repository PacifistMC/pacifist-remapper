package me.rancraftplayz.pacifist.remapper;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class RemapperPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getConfigurations().create("mojangProguardMappings");
        project.getConfigurations().create("spigotCsrgMappings");
        project.getConfigurations().create("remapLib");

        project.getConfigurations().create("accessWidener");
        project.getConfigurations().create("accessWidenerLib");

        project.getTasks().create("remapJar", RemapJarTask.class);
        project.getTasks().create("applyAccessWidener", ApplyAccessWidenerTask.class);
    }
}
