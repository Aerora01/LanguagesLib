package it.nikotecnology.languageslib;

import com.nikotecnology.nikolibs.managers.ConfigManager;
import com.nikotecnology.nikolibs.utils.Logger;
import it.nikotecnology.languageslib.commands.LanguageCommand;
import it.nikotecnology.languageslib.managers.TranslationManager;
import it.nikotecnology.languageslib.objects.Default;
import it.nikotecnology.languageslib.objects.LanguagesConfig;
import it.nikotecnology.languageslib.objects.Placeholder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.mattstudios.mf.base.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Developed by Nikotecnology.
 * You are not allowed to decompile or resell this plugin as yours or anything else.
 *
 * @author Nikotecnology
 */

public final class LanguagesLib extends JavaPlugin {

    @Getter private static LanguagesLib instance;
    @Getter @Setter
    private static String language;
    private List<String> plusing;

    public static List<String> colorList(List<String> stringList) {
        List<String> colored = new ArrayList<>();
       for(String str: stringList) {
           colored.add(color(str));
       }
       return colored;
    }


    @SneakyThrows
    @Override
    public void onEnable() {
        Logger.log(Logger.LogLevel.INFO, "Loading Plugin");
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        TranslationManager.registerLangs();
        CommandManager manager = new CommandManager(this);
        manager.getCompletionHandler().register("#plugins", input -> Arrays.stream(Bukkit.getPluginManager().getPlugins()).map(Plugin::getName).collect(Collectors.toList()));
        manager.getCompletionHandler().register("#locales", input -> TranslationManager.langs);
        manager.register(new LanguageCommand());
        Logger.log(Logger.LogLevel.SUCCESS, "Plugin loaded successfully");
    }

     public static void makeLanguageFile(Plugin plugin) throws IOException {
        LanguagesConfig config = new LanguagesConfig(plugin);
        if(config.getPathLang() == null) return;
        if(plugin.getConfig().getString(config.getPathLang()) == null) Logger.log(Logger.LogLevel.ERROR,
                "The language field in che config of the plugin: " + plugin.getName() + " does not exists! Check if you have implemented Language Interface in the Main class of your plugin!" );
         ;
        String pathlang = plugin.getConfig().getString(config.getPathLang()).toLowerCase();
        if(TranslationManager.langs.contains(pathlang))
            Logger.log(Logger.LogLevel.ERROR,
                    "The language field in che config of the plugin " + plugin.getName() + " contains an non-existing language.\n Languages available: " + TranslationManager.formatList(TranslationManager.langs));
        YamlConfiguration messagesConfig;

        if(getPluginLangFile(config).exists()) {
            File messages = getPluginLangFile(config);

            messagesConfig  = YamlConfiguration.loadConfiguration(messages);
            messagesConfig.save(messages);
        } else {
            File folder = new File(plugin.getDataFolder() + "//locales");
            if(folder.mkdir()) {
                File lang = getPluginLangFile(config);
                if (lang.createNewFile()) {
                     messagesConfig = YamlConfiguration.loadConfiguration(lang);
                    messagesConfig.save(lang);
                }
            }
        }
    }

    public static void generateDefaults(LanguagesConfig config) throws IOException {
            File messages = getPluginLangFile(config);
            YamlConfiguration messagesConfig;

            messagesConfig  = YamlConfiguration.loadConfiguration(messages);
            for (Default def : config.getDefaults()) {
                if(def.getStringList() == null) {
                    messagesConfig.set(def.getPath(), def.getMessage());
                } else {
                    messagesConfig.set(def.getPath(), def.getStringList());
                }
            }
            messagesConfig.save(messages);
    }

    public static File getPluginLangFile(LanguagesConfig config) {
        if (config.getPlugin() == null) return null;
        if (config.getPathLang() == null) return null;
        return new File(config.getPlugin().getDataFolder() + "//locales", config.getPlugin().getConfig().getString(config.getPathLang()) + ".yml");
    }

    public static YamlConfiguration getPluginLangFileConfiguration(LanguagesConfig config) {
        if (config.getPlugin() == null) return null;

        File lang = getPluginLangFile(config);
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        return conf;
    }


    static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String formatList(final List<String> list) {
        if (list.isEmpty()) {
            return "No one";
        }
        return list.toString().replaceAll("\\[", "").replaceAll("]", "");
    }

    @Override
    public void onDisable() {
        Logger.log(Logger.LogLevel.INFO, "Disabling Plugin");
    }
}
