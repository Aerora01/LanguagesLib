package it.nikotecnology.languageslib;

import com.nikotecnology.nikolibs.NikoLibs;
import com.nikotecnology.nikolibs.utils.Logger;
import it.nikotecnology.languageslib.objects.LanguagesConfig;
import it.nikotecnology.languageslib.objects.Placeholder;
import org.bukkit.configuration.file.YamlConfiguration;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Language {

    private LanguagesConfig config;
    private YamlConfiguration conf;

    public Language(LanguagesConfig config) {
        this.config = config;
        conf = LanguagesLib.getPluginLangFileConfiguration(config);
    }

    /**
     * Get String from plugin language
     *
     * @param path The Path on the language file
     * @return language text colored
     */
    public String getString(String path) {
        if(NikoLibs.isDebugging()) {
            Logger.log(Logger.LogLevel.DEBUG, "String path: " + path);
        }
        if(path == null) return null;
        if(conf == null) {
            Logger.log(Logger.LogLevel.DEBUG, config.getPlugin().getName() + " Config is null, String path: " + path);
            try {
                conf = LanguagesLib.getPluginLangFileConfiguration(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(conf.getString(path) != null) {
            return LanguagesLib.color(conf.getString(path));
        }
        return null;
    }

    /**
     * Get string list from plugin language
     *
     * @param path The Path on the language file
     * @return Colored List String from Language
     */
    public List<String> getStringList(String path) {
        if(NikoLibs.isDebugging()) {
            Logger.log(Logger.LogLevel.DEBUG, "String path: " + path);
        }
        if(path == null) return null;
        if(conf == null) {
            Logger.log(Logger.LogLevel.DEBUG, config.getPlugin().getName() + " Config is null, String path: " + path);
            try {
                conf = LanguagesLib.getPluginLangFileConfiguration(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(conf.getString(path) != null) {
            return LanguagesLib.colorList(conf.getStringList(path));
        }
        return null;
    }

    /**
     * Get string from plugin language selected and applies to it you're custom placeholders.
     * Make sure to set in the config the placeholders Fixes
     *
     * @param path The language path
     * @param placeholders The placeholders
     * @return The text with placeholders and colors applied
     */
    public String getReplaceTags(String path, Placeholder... placeholders) {
        if(NikoLibs.isDebugging()) {
            Logger.log(Logger.LogLevel.DEBUG, config.getPlugin().getName() + " getReplaceTags() with path: " + path + " with placeholders: " + Arrays.toString(placeholders));
        }
        if(path == null || placeholders == null) return null;
        if(conf == null) {
            Logger.log(Logger.LogLevel.DEBUG, config.getPlugin().getName() + " Config is null, String path: " + path);
            try {
                conf = LanguagesLib.getPluginLangFileConfiguration(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String newString = conf.getString(path);
        if(newString != null) {
            for (Placeholder placeholder : placeholders) {
                if(!newString.startsWith(config.getPlaceholderFix()) || !newString.endsWith(config.getPlaceholderFix())) return null;
                String plHolder = config.getPlaceholderFix() + placeholder.getPlaceholder() + config.getPlaceholderFix();
                newString = newString.replace(plHolder, placeholder.getValue());
            }
            return LanguagesLib.color(newString);
        }
        return null;
    }


    /**
     * Get string from plugin language selected and turn it to a TextComponent,
     * You can use NikosLib(Implemented in LanguagesLib Plugin) TextComponentBuilder.
     * Make sure the string is null otherwise this method don't work
     *
     * @param path The language path
     * @param component The TextComponent
     * @return TextComponent
     */
    public TextComponent getTextComponent(String path, TextComponent component) {
        if(NikoLibs.isDebugging() && path == null || component == null) {
            Logger.log(Logger.LogLevel.DEBUG, config.getPlugin().getName() + " getTextComponent() with null component or null path");
        }
        if (path == null || component == null) return null;
        if(conf == null) {
            try {
                conf = LanguagesLib.getPluginLangFileConfiguration(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String text = conf.getString(path);
        if(component.getText() != null) return null;

        if(text != null) {
            component.setText(text);
            return component;
        }

        return null;
    }
}
