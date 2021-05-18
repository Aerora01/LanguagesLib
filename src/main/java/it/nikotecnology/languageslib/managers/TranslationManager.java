package it.nikotecnology.languageslib.managers;

import java.util.ArrayList;
import java.util.List;

/**
 * Developed by Nikotecnology.
 * You are not allowed to decompile or resell this plugin as yours or anything else.
 *
 * @author Nikotecnology
 */
public class TranslationManager {

    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
    public static List<String> langs = new ArrayList<>();

    //Translate the plugin from the current language
//    public static String translatePlugin(LanguagesConfig confi, String language) throws Exception {
//        if(!confi.getPlugin().getDescription().getDepend().contains("LanguagesLib")) {
//            return "§c(§4!§c) §4That plugin isn't using LanguagesLib!";
//        }
//        File messages = LanguagesLib.getPluginLangFile(confi);
//        YamlConfiguration config = YamlConfiguration.loadConfiguration(messages);
//        File newLang = new File(confi.getPlugin().getDataFolder() + "//locales", language + ".yml");
//        YamlConfiguration nlang = YamlConfiguration.loadConfiguration(newLang);
//        for(String key : config.getConfigurationSection("").getKeys(false)) {
//            nlang.set(key, translate(confi.getPlugin().getConfig().getString("Lang"), language, config.getString(key)));
//        }
//        nlang.save(newLang);
//        return "§a(§2!§a) §2Plugin translated succesfully";
//    }

//    private static String translate(String fromLang, String toLang, String text) throws Exception {
//        String jsonPayload = new StringBuilder()
//                .append("{")
//                .append("\"fromLang\":\"")
//                .append(fromLang)
//                .append("\",")
//                .append("\"toLang\":\"")
//                .append(toLang)
//                .append("\",")
//                .append("\"text\":\"")
//                .append(text)
//                .append("\"")
//                .append("}")
//                .toString();
//
//        URL url = new URL(ENDPOINT);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setDoOutput(true);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
//        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
//        conn.setRequestProperty("Content-Type", "application/json");
//
//        OutputStream os = conn.getOutputStream();
//        os.write(jsonPayload.getBytes());
//        os.flush();
//        os.close();
//
//        int statusCode = conn.getResponseCode();
//        if(NikoLibs.isDebugging()) {
//            System.out.println("Status Code: " + statusCode);
//        }
//        if(statusCode == 400) return null;
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
//        ));
//        String output;
//        while ((output = br.readLine()) != null) {
//            return output;
//        }
//        conn.disconnect();
//        return null;
//    }

    public static void registerLangs() {
        langs.add("af");
        langs.add("ar");
        langs.add("az");
        langs.add("be");
        langs.add("bg");
        langs.add("bn");
        langs.add("bs");
        langs.add("ca");
        langs.add("ceb");
        langs.add("cs");
        langs.add("cy");
        langs.add("da");
        langs.add("de");
        langs.add("el");
        langs.add("en");
        langs.add("eo");
        langs.add("es");
        langs.add("et");
        langs.add("eu");
        langs.add("fa");
        langs.add("fi");
        langs.add("fr");
        langs.add("ga");
        langs.add("gl");
        langs.add("gu");
        langs.add("ha");
        langs.add("hi");
        langs.add("hmn");
        langs.add("hr");
        langs.add("hu");
        langs.add("hy");
        langs.add("id");
        langs.add("ig");
        langs.add("is");
        langs.add("it");
        langs.add("iw");
        langs.add("ja");
        langs.add("jw");
        langs.add("ka");
        langs.add("kk");
        langs.add("km");
        langs.add("kn");
        langs.add("ko");
        langs.add("la");
        langs.add("lo");
        langs.add("lt");
        langs.add("lv");
        langs.add("ma");
        langs.add("mg");
        langs.add("mi");
        langs.add("mk");
        langs.add("ml");
        langs.add("mn");
        langs.add("mr");
        langs.add("ms");
        langs.add("mt");
        langs.add("my");
        langs.add("ne");
        langs.add("nl");
        langs.add("no");
        langs.add("ny");
        langs.add("pl");
        langs.add("pt");
        langs.add("ro");
        langs.add("ru");
        langs.add("si");
        langs.add("sk");
        langs.add("sl");
        langs.add("so");
        langs.add("sq");
        langs.add("sr");
        langs.add("st");
        langs.add("su");
        langs.add("sv");
        langs.add("sw");
        langs.add("ta");
        langs.add("te");
        langs.add("tg");
        langs.add("th");
        langs.add("tl");
        langs.add("tr");
        langs.add("uk");
        langs.add("ur");
        langs.add("uz");
        langs.add("vi");
        langs.add("yi");
        langs.add("yo");
        langs.add("zh-CN");
        langs.add("zh-TW");
        langs.add("zu");
    }

    public static String formatList(final List<String> list) {
        if (list.isEmpty()) {
            return "Nessuno";
        }
        return list.toString().replaceAll("\\[", "").replaceAll("]", "");
    }

}
