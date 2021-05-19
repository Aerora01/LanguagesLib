package it.nikotecnology.languageslib.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> langs = new ArrayList<>();

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
