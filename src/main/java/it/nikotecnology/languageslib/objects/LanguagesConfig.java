package it.nikotecnology.languageslib.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.Plugin;

/**
 * Developed by Nikotecnology.
 * You are not allowed to decompile or resell this plugin as yours or anything else.
 *
 * @author Nikotecnology
 */

public class LanguagesConfig {

    @Getter
    private Plugin plugin;
    /**
     * The PlaceHolder prefix and suffix. Example: config.setPlaceholderFix("%");
     * In your language file/default you need to set the placeholder with that prefix and suffix like this:
     * "%mycoolplaceholder%" and when you call the getReplaceTags you need to set only the prefix in this case
     * "mycoolplaceholer" without the fixes
     *
     */
    @Getter @Setter
    private String placeholderFix;
    /**
     * The plugin default language messages
     */
    @Getter @Setter
    private Default[] defaults;
    /**
     * The plugin default language
     */
    @Getter @Setter
    private String defaultLanguage;
    /**
     * The plugin lang selector path in the config
     */
    @Getter @Setter
    private String pathLang;

    public LanguagesConfig(Plugin plugin) {
        this.plugin = plugin;
    }

}
