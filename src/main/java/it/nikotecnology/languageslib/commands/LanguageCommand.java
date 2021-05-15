package it.nikotecnology.languageslib.commands;

import com.nikotecnology.nikolibs.builders.TextComponentBuilder;
import it.nikotecnology.languageslib.LanguagesLib;
import it.nikotecnology.languageslib.managers.TranslationManager;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Completion;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Developed by Nikotecnology.
 * You are not allowed to decompile or resell this plugin as yours or anything else.
 *
 * @author Nikotecnology
 */
@Command("languagelib")
public class LanguageCommand extends CommandBase {
    private HashMap<UUID, Long> cooldown = new HashMap<>();
    private List<UUID> transConfirm = new ArrayList<>();
    private int cooltime = 30;

    @SubCommand("regenerate")
    @Permission("languagelib.command.regenerate")
    public void regenerateCommand(final CommandSender sender) throws IOException {
        if(!(sender instanceof Player)) return;
        Player p = (Player) sender;
        if(cooldown.containsKey(p.getUniqueId())) {
            long secondslft = ((cooldown.get(p.getUniqueId()) / 1000) + cooltime) - (System.currentTimeMillis() / 1000);
            if(secondslft > 0) {
                p.sendMessage("§c(§4!§c) §4To execute this command you need to wait " + secondslft + " seconds!");
            }
        } else {
            for (Plugin plugin : Bukkit.getServer().getPluginManager().getPlugins()) {
                if (plugin.getDescription().getDepend().contains("LanguagesLib")) {
                    LanguagesLib.makeLanguageFile(plugin);
                }
            }

            sender.sendMessage("§a(§2!§a) §2Langs Reloaded!");
        }
    }

    @SubCommand("translate")
    @Permission("languagelib.command.translate")
    public void translateCommand(final CommandSender sender, @Completion("#plugins") String plugin, @Completion("#locales") String lang) throws Exception {
        if(!(sender instanceof Player)) return;
        Player p = (Player) sender;
        sender.sendMessage("§4⚠️ ATTENTION!\n\n §7This library feature is in beta! Don't use this if you aren't sure what are you doing!\n The actual Translation API Limits the user to 10 translations, currently no other company has a free translation system, only for a fee, in the future we will try to create a proprietary system\n");
        TextComponent confirm = new TextComponentBuilder()
                .setText("[i'm sure what i'm doing]")
                .setTextColor(ChatColor.DARK_RED)
                .setHoverText("§7Click me to confirm")
                .setCommand("languageslib translateconfirm " + plugin + " " + lang + " ")
                .build();
        sender.spigot().sendMessage(confirm);
        transConfirm.add(p.getUniqueId());
    }

    @SubCommand("translateconfirm")
    @Permission("languagelib.command.translate")
    public void translateConfirmCommand(final CommandSender sender, @Completion("#plugins") String plugin, @Completion("#locales") String lang) throws Exception {
        if(!(sender instanceof Player)) return;
        Player p = (Player) sender;
        if(!TranslationManager.langs.contains(lang))
            sender.sendMessage("§4⚠️The language inserted does not exists!");
        Plugin pl = Bukkit.getPluginManager().getPlugin(plugin);
        if(pl != null) {
            File langs = new File(pl.getDataFolder() + "//locales", pl.getConfig().getString("Lang"));
            if(langs.exists()) {
                sender.sendMessage("§c(§4!§c) §4This language already exists!");
            }
            sender.sendMessage("§a(§2!§a) §2Plugin translation started! It may take a few minutes, don't use the plugin before the translation finish!");
            sender.sendMessage(TranslationManager.translatePlugin(pl, lang));
        }
        transConfirm.remove(p.getUniqueId());
    }

}
