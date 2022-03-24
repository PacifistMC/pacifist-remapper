package io.github.rancraftplayz.remapper;

import me.rancraftplayz.mappingsconverter.ApplyAccessWidener;
import me.rancraftplayz.mappingsconverter.MojangSpigotAccessWidenerRemapper;
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

public class ApplyAccessWidenerTask extends DefaultTask {
    @TaskAction
    void applyAccessWidenerTask() throws IOException {
        Set<File> alibFiles = getProject().getConfigurations().named("accessWidenerLib").get().getFiles();
        List<Path> alibs = new ArrayList<>();

        for (File file : alibFiles) {
            alibs.add(file.toPath());
        }

        String mcVersion = RemapperPlugin.extension.getVersion();

        Optional<File> accessWidener = getProject().getConfigurations().named("accessWidener").get().getFiles().stream().findFirst();

        if (!accessWidener.isPresent()) {
            System.out.println("Access Widener not found!");
            return;
        }

        if (!mcVersion.isEmpty()) {
            MojangSpigotAccessWidenerRemapper.remap(accessWidener.get().toPath(), RemapperPlugin.mappingsDir, mcVersion, alibs);
        } else {
            System.out.println("Mappings not found! Applying access wideners without mappings");
            applyAccessWidenersWithoutMaps(accessWidener.get(), alibs);
        }
    }

    private void applyAccessWidenersWithoutMaps(File accessWidener, List<Path> libs) {
        ApplyAccessWidener applier = new ApplyAccessWidener(accessWidener);

        for (Path omg : libs) {
            applier.apply(omg.toFile());
        }
    }
}
