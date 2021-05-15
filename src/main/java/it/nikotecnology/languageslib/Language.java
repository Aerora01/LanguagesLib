package it.nikotecnology.languageslib;

import it.nikotecnology.languageslib.objects.LanguagesConfig;
import it.nikotecnology.languageslib.objects.Placeholder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.awt.*;
import java.util.List;
import java.util.Set;

public class Language {

    private Plugin plugin;
    private YamlConfiguration conf;

    public Language(Plugin plugin) {
        this.plugin = plugin;
        conf = LanguagesLib.getPluginLangFileConfiguration(plugin);
    }

    /**
     * Get String from plugin language
     *
     * @param path The Path on the language file
     * @return language text colored
     */
    public String getString(String path) {
        if(plugin == null || path == null || conf == null) return null;
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
        if(plugin == null || path == null || conf == null) return null;
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
        if(plugin == null || path == null || placeholders == null) return null;
        YamlConfiguration conf = LanguagesLib.getPluginLangFileConfiguration(plugin);
        LanguagesConfig config = new LanguagesConfig(plugin);
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
        if (plugin == null || path == null || component == null) return null;
        YamlConfiguration conf = LanguagesLib.getPluginLangFileConfiguration(plugin);
        String text = conf.getString(path);
        if(component.getText() != null) return null;

        if(text != null) {
            component.setText(text);
            return component;
        }

        return null;
    }
}
