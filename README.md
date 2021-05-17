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
```
