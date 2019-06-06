package de.android.ayrathairullin.myapplication;

import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AI {
    public static String getAnswer(String s) {
        Map<String, String> dataBase = new HashMap<String, String>() {{
            put("hi", "Hallo");
            put("how are you", "Thanks, fine!");
            put("where are you from?", "I\'m from USA");
        }};

        s = s.toLowerCase().trim();
        List<String> answers = new ArrayList<>();


        for (String string : dataBase.keySet()) {
            if (s.contains(string)) {
                answers.add(dataBase.get(string));
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (answers.isEmpty()) answers.add("Ok");
            return String.join(", ", answers);
        } else {
            if (s.contains("hi")) {
                return "Hallo";
            }
            if (s.contains("how are you")) {
                return "Thanks, fine!";
            }
            if (s.contains("where are you from?")) {
                return "I\'m from USA";
            }
            return "Ok";
        }
    }
}
