# Languages Lib

> Make language implementation more easier in your plugin!

To use this library you have to download it from the Relases page, Make your plugin depend it and insert this code in your plugin main:

```JAVA

 public static LanguagesConfig langconfig = new LanguagesConfig(MyCoolPlugin.getInstance());

 public void setLangConfig() throws IOException {
         //This is the config path you will use in your plugin to set the language
         langconfig.setPathLang("Lang");          
         /**
           * The PlaceHolder prefix and suffix.
           * In your language file/default you need to set the placeholder with that prefix and suffix like this:
           * "%mycoolplaceholder%" and when you call the getReplaceTags you need to set only the prefix in this case
           * "mycoolplaceholer" without the fixes
           *
         */
         langconfig.setPlaceholderFix("%");  
         //The default language of your plugin
         langconfig.setDefaultLanguage("en");
         langconfig.setDefaults(new Default[]{
                 //Call this class to set a default(first parameter: path, second parameter: message)
                 new Default("my-cool-message", "&6My Cool Message");
                 ...
                 //This defaults will be inserted in the default language file, in this case, the en lang
         });
         //Don't remove this, it generates the plugin default language!
         LanguagesLib.generateDefaults(langconfig);
     }
     
 public static LanguagesConfig getLangConfig() {
    return config;
 }
```

Than you need to implement the Language class in every file you'll need to use the language like this class in the example project:


```JAVA
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

```


The class Language has this various methods:


   **getString(String path):**<br>
             > This method allows you to get a message from config with colors


   **getStringList(String path)**<br>
             > This method allows you to get a string list from the config(returns a list)
        
        
   **getReplaceTags(String path, Placeholder... placeholders)**<br>
             > This method allows you to replace placeholders in your config message,<br>
             > to add a placeholder you'll need to insert: <br>
 ```JAVA
             
 lang.getReplaceTags("my-path", new Placeholder("player", player.getName()));
               
 ```
 
  You noticed that the placeholder "player" doesen't have any fixes, this because you'll need to set it in the config, like written up 



If you want to review some other functions of the library open the [**Example Project**](https://github.com/Nikotecnology/LanguagesLib/tree/master/Example%20Project)
