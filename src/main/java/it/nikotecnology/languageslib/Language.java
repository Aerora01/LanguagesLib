package it.nikotecnology.languageslib;

import it.nikotecnology.languageslib.objects.LanguagesConfig;
import it.nikotecnology.languageslib.objects.Placeholder;
import it.nikotecnology.languageslib.utils.Logger;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class Language {

    private LanguagesConfig config;
    private YamlConfiguration conf;

    public Language(LanguagesConfig config) {
        this.config = config;
        conf = LanguagesLib.getPluginLangFileConfiguration(this.config);
    }

    /**
     * Get String from plugin language
     *
     * @param path The Path on the language file
     * @return language text colored
     */
    public String getString(String path) {
        if (isConfigNull()) return null;
        if (isPathNull(path)) return null;
        if(conf.getString(path) != null) {
            return LanguagesLib.color(conf.getString(path));
        }
        Logger.log(Logger.LogType.ERROR, "A player tried to receive a message, but in the path there isn't a message");
        return null;
    }

    /**
     * Get string list from plugin language
     *
     * @param path The Path on the language file
     * @return Colored List String from Language
     */
    public List<String> getStringList(String path) {
        if (isConfigNull()) return null;
        if (isPathNull(path)) return null;
        if(conf.getString(path) != null) {
            return LanguagesLib.colorList(conf.getStringList(path));
        }
        Logger.log(Logger.LogType.ERROR, "A player tried to receive a string list, but in the path there isn't a list");
        return null;
    }

    private boolean isPathNull(String path) {
        if(path == null) {
            Logger.log(Logger.LogType.ERROR, "A player tried to receive a message with null path");
            return true;
        }
        if(conf == null) {
            try {
                conf = LanguagesLib.getPluginLangFileConfiguration(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
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
        if (isConfigNull()) return null;
        if(path == null || placeholders == null) {
            Logger.log(Logger.LogType.ERROR, "A player tried to receive a message with null path or a null placeholder");
            Logger.log(Logger.LogType.TIP, "If you want to send a message without placeholder/s use getString() method instead");
            return null;
        }
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
                if (!plHolder.startsWith(config.getPlaceholderFix()) || !plHolder.endsWith(config.getPlaceholderFix())) {
                    Logger.log(Logger.LogType.ERROR, "The placeholder needs to start with the current placeholder fix: " + config.getPlaceholderFix());
                    return null;
                }


                newString = newString.replace(plHolder, placeholder.getValue());
            }
            return LanguagesLib.color(newString);
        }
        Logger.log(Logger.LogType.ERROR, "A player tried to receive a message with placeholders, but in the path there isn't a message");
        return null;
    }


    /**
     * Get string from plugin language selected and turn it to a TextComponent,
     * You can use NikosLib(Implemented in LanguagesLib Plugin) TextComponentBuilder.
     * Make sure the TextComponent text is null otherwise this method don't work
     *
     * @param path The language path
     * @param component The TextComponent
     * @return TextComponent
     */
    public TextComponent getTextComponent(String path, TextComponent component) {
        if (isConfigNull()) return null;
        if (path == null || component == null) {
            Logger.log(Logger.LogType.ERROR, "A player tried to receive a message with null path or a null TextComponent");
            Logger.log(Logger.LogType.TIP, "If you want to send a message without TextComponent use getString() method instead");
            return null;
        }
        if(conf == null) {
            try {
                conf = LanguagesLib.getPluginLangFileConfiguration(config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String text = conf.getString(path);
        if(component.getText() != null) {
            Logger.log(Logger.LogType.ERROR, "You can't pass to getTextComponent() a TextComponent with text, remove it!");
            return null;
        }

        if(text != null) {
            component.setText(text);
            return component;
        }
        Logger.log(Logger.LogType.ERROR, "A player tried to receive a message with a TextComponent, but in the path there isn't a message");
        return null;
    }

    private boolean isConfigNull() {
        if(LanguagesLib.configNull(config)) {
            Logger.log(Logger.LogType.ERROR, "Something on the LanguagesConfig is null! Compile all the following methods referring to the readme: ");
            if(config.getPlaceholderFix() == null) {
                Logger.log(Logger.LogType.ERROR, "PlaceholderFix");
            }
            if(config.getPlugin() == null) {
                Logger.log(Logger.LogType.ERROR, "Plugin");
            }
            if(config.getPathLang() == null) {
                Logger.log(Logger.LogType.ERROR, "PathLang");
            }
            if(config.getDefaultLanguage() == null) {
                Logger.log(Logger.LogType.ERROR, "DefaultLanguage");
            }
            return true;
        }
        return false;
    }
}
