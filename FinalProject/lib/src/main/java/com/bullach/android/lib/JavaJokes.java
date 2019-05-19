package com.bullach.android.lib;

import java.util.Random;

public class JavaJokes {

    private static String[] jokes = {
            "Eight bytes walk into a bar. The bartender asks, 'Can I get you anything?'" +"\n" + "'Yeah,' reply the bytes. 'Make us a double.'",
            "How many programmers does it take to change a light bulb?" +"\n" + "None – It’s a hardware problem",
            "Programming is like sex: " +"\n" + "One mistake and you have to support it for the rest of your life."
    };

    public String generateJoke() {
        int joke_id = new Random().nextInt(jokes.length);
        return jokes[joke_id];
    }
}
