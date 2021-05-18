package it.nikotecnology.languageslib;

import it.nikotecnology.languageslib.managers.TranslationManager;
import it.nikotecnology.languageslib.objects.Default;
import it.nikotecnology.languageslib.objects.LanguagesConfig;
import it.nikotecnology.languageslib.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Developed by Nikotecnology.
 * You are not allowed to decompile or resell this plugin as yours or anything else.
 *
 * @author Nikotecnology
 */

public class LanguagesLib {

    public static Language getLanguage(LanguagesConfig config) {
        return new Language(config);
    }

    public static boolean makeLanguageFile(LanguagesConfig config) throws IOException {
        if(configNull(config)) return false;
        if(config.getPlugin().getConfig().getString(config.getPathLang()) == null) {
            Logger.log(Logger.LogLevel.ERROR,
                    "The language field in che config of the plugin: " + config.getPlugin().getName() + " does not exists! Check if you have implemented Language Interface in the Main class of your plugin!" );
            return false;
        }

        String pathlang = config.getPlugin().getConfig().getString(config.getPathLang()).toLowerCase();
        if(TranslationManager.langs.contains(pathlang)) {
            Logger.log(Logger.LogLevel.ERROR,
                    "The language field in che config of the plugin " + config.getPlugin().getName() + " contains an non-existing language.\n Languages available: " + TranslationManager.formatList(TranslationManager.langs));
            return false;
        }

        YamlConfiguration messagesConfig;

        if(!getPluginLangFile(config).exists()) {
            File folder = new File(config.getPlugin().getDataFolder() + "//locales");
            if(!folder.exists()) {
                if (folder.mkdir()) {
                    File lang = getPluginLangFile(config);
                    if (!lang.exists()) {
                        if (lang.createNewFile()) {
                            messagesConfig = YamlConfiguration.loadConfiguration(lang);
                            return gendefaults(config, messagesConfig, lang);
                        }
                    }
                }
            }
        } else {
            File lang = getPluginLangFile(config);
            messagesConfig = YamlConfiguration.loadConfiguration(lang);
            Map<String, Object> configValues = messagesConfig.getValues(false);
            for (Map.Entry<String, Object> entry : configValues.entrySet()) {
                messagesConfig.set(entry.getKey(), null);
            }
            return gendefaults(config, messagesConfig, lang);
        }
        return false;
    }

    private static boolean gendefaults(LanguagesConfig config, YamlConfiguration messagesConfig, File lang) throws IOException {
        for (Default def : config.getDefaults()) {
            if (def.getStringList() == null) {
                messagesConfig.set(def.getPath(), def.getMessage());
            } else {
                messagesConfig.set(def.getPath(), def.getStringList());
            }
        }
        messagesConfig.save(lang);
        return true;
    }

    protected static File getPluginLangFile(LanguagesConfig config) {
        if (config.getPlugin() == null) return null;
        if (config.getPathLang() == null) return null;
        return new File(config.getPlugin().getDataFolder() + "//locales", config.getPlugin().getConfig().getString(config.getPathLang()) + ".yml");
    }

    protected static YamlConfiguration getPluginLangFileConfiguration(LanguagesConfig config) {
        if (config.getPlugin() == null) return null;

        File lang = getPluginLangFile(config);
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        return conf;
    }


    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }


    private static boolean configNull(LanguagesConfig config) {
        return config.getPlugin() == null
                && config.getPlaceholderFix() == null
                && config.getPathLang() == null
                && config.getDefaultLanguage() == null
                && config.getDefaults() == null;
    }

    public static void reloadLang(LanguagesConfig config) throws IOException {
        if(makeLanguageFile(config)) {
            Logger.log(Logger.LogLevel.SUCCESS, "Language reloaded succesfully!");
            return;
        }

        Logger.log(Logger.LogLevel.ERROR, "Language cannot be reloaded! Try again!");
    }

    public static List<String> colorList(List<String> stringList) {
         if(stringList == null) return null;
        List<String> colored = new ArrayList<>();
        for(String str: stringList) {
            colored.add(color(str));
        }
        return colored;
    }
}