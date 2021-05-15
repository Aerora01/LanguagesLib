package it.nikotecnology.languageslib.interfaces;

import it.nikotecnology.languageslib.LanguagesLib;
import it.nikotecnology.languageslib.objects.LanguagesConfig;
import org.bukkit.plugin.Plugin;

/**
 * Developed by Nikotecnology. You are not allowed to decompile or resell this plugin as yours or anything else.
 *
 * @author Nikotecnology
 */
public interface Language {

    static LanguagesLib getLang() {
        return LanguagesLib.getInstance();
    }

     default void setLangConfig(Plugin plugin) {
        LanguagesConfig config = new LanguagesConfig(plugin);
        if(plugin.getConfig().getString(config.getPathLang()) == null) {
            plugin.getConfig().set(config.getPathLang(), config.getDefaultLanguage());
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
        }
    }

}
