package me.example.exampleproject.commands;

import com.nikotecnology.nikolibs.builders.TextComponentBuilder;
import it.nikotecnology.languageslib.Language;
import it.nikotecnology.languageslib.objects.Placeholder;
import me.example.exampleproject.ExampleProject;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExampleCommand implements CommandExecutor {
    private Language lang = new Language(ExampleProject.getLangConfig());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(lang.getString("console-error"));
            return true;
        }
        Player p = (Player) sender;

        if(args.length < 1) {
            p.sendMessage(lang.getString("cool-message"));
        } else if(args.length > 2) {
            p.spigot().sendMessage(lang.getTextComponent("cool-component", new TextComponentBuilder()
                .setHoverText(ChatColor.translateAlternateColorCodes('&', "&7You are a good person!"))
                .setCommandSuggestion("examplecommand " + p.getName())
                .build()
            ));
        } else {
            p.sendMessage(lang.getReplaceTags("cool-message-player", new Placeholder("player", args[0])));
        }
        return false;
    }
}
