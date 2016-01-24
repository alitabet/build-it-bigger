package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a joke factory class. The jokes are fom
 * http://academictips.org/funny-jokes/funny-short-jokes/
 * Every time a new joke is requested,
 * the factory randomly chooses a joke from the list.
 *
 * @author Ali K Thabet
 */
public class JokeFactory {

    private static final List<String> jokes = new ArrayList<>(); // list of all jokes
    private Random random;

    static {
        jokes.add("Dad: Shame on you, Peter. Why did you hit your little sister?\n" +
                  "Peter: Well, Daddy, we were playing Adam and Eve with the apple and all. Well, instead of tempting me with that apple, she ate the thing herself!");
        jokes.add("Mary, why did you kick your brother in the stomach?! exclaimed the angry mother.\n" +
                  "It was pure accident, Mama. He turned around.");
        jokes.add("Mama, there's a man at the door, said little Johnny.\n" +
                  "He says he's collecting for senior citizens. Do you think we should hide Grandpa?");
        jokes.add("Mrs. Peterson went to the doctor: I'm terribly worried about my boy. He thinks he's a chicken.\n" +
                  "The doctor asked, And how long has this been going on?\n" +
                  "Almost a year, Mrs. Peterson replied.\n" +
                  "Well for goodness sakes! Why didn't you bring him to see me sooner?\n" +
                  "Because we needed the eggs!");
        jokes.add("It was the end of the school year and Joey's mother asked: And were the exam questions difficult?\n" +
                  "They weren't bad at all, her son replied. It was the answers that gave me all the trouble.");
    }

    public JokeFactory() {
        random = new Random(); // used to read random joke
    }

    // get a random joke from the list
    public String getJoke() {
        if (jokes.isEmpty()) return "No jokes available";

        int index = random.nextInt(jokes.size()); // get a random joke index
        return jokes.get(index);
    }
}
