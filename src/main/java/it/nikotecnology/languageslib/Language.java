package it.nikotecnology.languageslib;

import it.nikotecnology.languageslib.objects.LanguagesConfig;
import it.nikotecnology.languageslib.objects.Placeholder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.YamlConfiguration;

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
        if(path == null) return null;
        if(conf == null) {
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
        if(path == null) return null;
        if(conf == null) {
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
        if(path == null || placeholders == null) return null;
        if(conf == null) {
            try {
                conf = LanguagesLib.getPluginLangFileConfiguration(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String newString = conf.getString(path);
        if(newString != null) {
            for (Placeholder placeholder : placeholders) {
                String plHolder = config.getPlaceholderFix() + placeholder.getPlaceholder() + config.getPlaceholderFix();
                if (!plHolder.startsWith(config.getPlaceholderFix()) || !plHolder.endsWith(config.getPlaceholderFix()))
                    return null;

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
