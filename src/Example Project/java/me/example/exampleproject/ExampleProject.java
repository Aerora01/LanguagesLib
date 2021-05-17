package me.example.exampleproject;

import it.nikotecnology.languageslib.LanguagesLib;
import it.nikotecnology.languageslib.objects.Default;
import it.nikotecnology.languageslib.objects.LanguagesConfig;
import me.example.exampleproject.commands.ExampleCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class ExampleProject extends JavaPlugin {

    public static LanguagesConfig config = new LanguagesConfig(getInstance());
    private static ExampleProject instance;

    @Override
    public void onEnable() {
        instance = this;
        config.setDefaultLanguage("en");
        config.setPlaceholderFix("%");
        config.setPathLang("lang");
        config.setDefaults(new Default[]{
                new Default("cool-message", "&6&lHey! You cool! Insert a name of a player in the command like this: /examplecommand [playername]"),
                new Default("console-error", "&cHey! You can execute this command only in-game!"),
                new Default("cool-message-player", "&e&lYeah! the player %player% is cool!"),
                new Default("cool-component", "&aHey pass the cursor over here, i show you something...")
        });
        getConfig().options().copyDefaults(true);
        saveConfig();
        try {
            LanguagesLib.generateDefaults(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.getCommand("examplecommand").setExecutor(new ExampleCommand());

        getLogger().info("Example plugin loaded!");
    }

    public static ExampleProject getInstance() {
        return instance;
    }

    public static LanguagesConfig getLangConfig() {
        return config;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
