package me.rancraftplayz.pacifist.remapper;

import me.rancraftplayz.mappingsconverter.MojangSpigotRemapper;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.bundling.AbstractArchiveTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class RemapJarTask extends DefaultTask {
    @TaskAction
    void remapJarTask() throws IOException {
        File inputJar = ((AbstractArchiveTask) getProject().getTasks().getByName("jar")).getArchiveFile().get().getAsFile();

        if (!inputJar.exists()) {
            System.out.println("Input jar not found!");
            return;
        }

        Set<File> libFiles = getProject().getConfigurations().named("remapLib").get().getFiles();
        List<Path> libs = new ArrayList<>();

        for (File file : libFiles) {
            libs.add(file.toPath());
        }

        Set<File> alibFiles = getProject().getConfigurations().named("accessWidenerLib").get().getFiles();
        List<Path> alibs = new ArrayList<>();

        for (File file : alibFiles) {
            alibs.add(file.toPath());
        }

        String mcVersion = RemapperPlugin.extension.getVersion();

        Optional<File> accessWidener = getProject().getConfigurations().named("accessWidener").get().getFiles().stream().findFirst();

        Path accessWidenerPath = null;
        if (accessWidener.isPresent()) {
            accessWidenerPath = accessWidener.get().toPath();
        }

        if (!mcVersion.isEmpty()) {
            MojangSpigotRemapper.remapAll(inputJar.toPath(), RemapperPlugin.mappingsDir, mcVersion, libs, accessWidenerPath, alibs);
        } else {
            System.out.println("Mappings not found!");
        }
    }
}
