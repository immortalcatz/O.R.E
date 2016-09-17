package com.acronym.ore.common.directories;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.common.helpers.Logger;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.util.Collection;

import static com.acronym.ore.common.reference.Reference.Directories.CONFIG_DIR;
import static com.acronym.ore.common.reference.Reference.Directories.SCRIPT_DIR;
import static com.acronym.ore.common.reference.Reference.ModInfo.NAME;

/**
 * Created by EwyBoy
 **/
public class Scripts {

    public static void loadScripts(FMLPreInitializationEvent event) {
        CONFIG_DIR = new File(event.getSuggestedConfigurationFile().getParent(), File.separator + NAME + File.separator);
        SCRIPT_DIR = new File(CONFIG_DIR, File.separator + "Generators" + File.separator);

        if (!SCRIPT_DIR.exists()) SCRIPT_DIR.mkdir();

        try {
            registerJsons();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerJsons() throws Exception {
        for (File script : SCRIPT_DIR.listFiles(file -> file.getName().endsWith(".json"))) {
            Logger.info("adding file: " + script.getName());
                JSONParser<Generation> readerGeneration = new JSONParser<>(script, Generation.class);
            Logger.info("reading file");
            addGenerator(readerGeneration.getElements("ore"));
        }
    }

    public static void addGenerator(Collection<? extends Generation> types) {
        for (Generation type : types) {
            Logger.info("adding type: " + type);
            Logger.info("adding" + type.getName());
            GenerationRegistry.addGeneration(type.register());
        }
    }
}
