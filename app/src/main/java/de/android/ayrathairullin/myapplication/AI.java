package de.android.ayrathairullin.myapplication;

import android.support.v4.util.Consumer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AI {
    private static String cityName;

    public static void getAnswer(String s, final Consumer<String> callback) {
        String answer = "";
//        Map<String, String> dataBase = new HashMap<String, String>() {{
//            put("hi", "Hallo");
//            put("how are you", "Thanks, fine!");
//            put("where are you from?", "I\'m from USA");
//        }};
//
        s = s.toLowerCase().trim();
//        List<String> answers = new ArrayList<>();
//
//
//        for (String string : dataBase.keySet()) {
//            if (s.contains(string)) {
//                answers.add(dataBase.get(string));
//            }
//        }

        Pattern cityPattern = Pattern.compile("погода (\\p{L}+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = cityPattern.matcher(s);
        if (matcher.find()) {
            cityName = matcher.group(1);
            Weather.Condition.getWeather(cityName, "ru", new Consumer<String>() {

                private String answer;

                @Override
                public void accept(String string) {
                    answer = string;
                    callback.accept(answer);
                }
            });
        }else {
            answer = "я не знаю погоду в городе " + cityName;
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (answers.isEmpty()) answers.add("Ok");
//            return String.join(", ", answers);
//        } else {
//            if (s.contains("hi")) {
//                return "Hallo";
//            }
//            if (s.contains("how are you")) {
//                return "Thanks, fine!";
//            }
//            if (s.contains("where are you from?")) {
//                return "I\'m from USA";
//            }
//            return "Ok";
//        }
        callback.accept(answer);
    }
}
