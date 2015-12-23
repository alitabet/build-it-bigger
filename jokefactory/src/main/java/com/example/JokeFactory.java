package com.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is a joke factory class. The class reads jokes
 * from a predefined JSON file, which contains a large
 * number of jokes. The jokes where obtained from
 * http://www.fortypoundhead.com/uploads/funny_jokes.rar.
 * The factory initializes by reading all jokes into
 * an ArrayList. Every time a new joke is requested,
 * the factory randomly chooses a joke from the list.
 *
 * @author Ali K Thabet
 */
public class JokeFactory {

    private static final String IO_EXCEPTION = "io exception";
    private static final String FILE_NOT_FOUND = "file not found";

    private ArrayList<String> jokes; // list of all jokes
    private String jokeJsonFileName = "C:\\Udacity - Android Nanodegree\\Build It Bigger\\jokefactory\\jokes.json";
    private Random random;

    private String jsonString = "{\"jokes\": [{\"ID\":\"1\",\"Category\":\"Random \\r\\njoke of the day\",\"Joke\":\"What's the difference between Windows \\r\\n95 and a \\r\\nvirus? \\r\\nA virus does something.\"}, {\"ID\":\"2\",\"Category\":\"Random joke of the day\",\"Joke\":\"Q: What goes VROOM, SCREECH,VROOM, \\r\\n\\r\\nSCREECH,VROOM, SCREECH?\\r\\nA: A blonde going through a flashing red \\r\\nlight.\"}, {\"ID\":\"3\",\"Category\":\"Email this funny joke to a friend!\",\"Joke\":\"What's the difference \\r\\n\\r\\nbetween Windows 95 and a virus? \\r\\nA virus does something.\"}, {\"ID\":\"4\",\"Category\":\"Funny jokes - 50 best jokes\",\"Joke\":\"Q: What goes VROOM, SCREECH,VROOM, \\r\\n\\r\\nSCREECH,VROOM, SCREECH?\\r\\nA: A blonde going through a flashing red \\r\\nlight.\"}, {\"ID\":\"5\",\"Category\":\"Funny jokes - 50 best jokes\",\"Joke\":\"The officer shouted \\r\\norders to a \\r\\nnearby soldier. With considerable bravery, the GI ran \\r\\ndirectly onto \\r\\nthe field of battle, in the line of fire, to retrieve a \\r\\ndispatch \\r\\ncase from a dead soldier. In a hail of bullets, he dove back to \\r\\n\\r\\nsafety.\\r\\n\\r\\n\\\"Private,\\\" the officer said, \\\"I'm recommending you for a \\r\\nmedal. You \\r\\nrisked your life to save the locations of our secret \\r\\nwarehouses.\\\"\\r\\n\\r\\n\\\"Warehouses?\\\" the private shouted. \\\"I thought you said \\r\\n\\r\\nwhorehouses!\\\"\"}, {\"ID\":\"6\",\"Category\":\"Funny jokes - 50 best jokes\",\"Joke\":\"\\\"I was married 3 times\\\" explained \\r\\nthe man to a newly discovered \\r\\ndrinking partner, \\\"and I'll never \\r\\nmarry again. My first 2 wives died \\r\\nof eating poison mushrooms and my \\r\\n3rd wife died of a fractured skull.\\\" \\r\\n\\r\\n\\\"That's a shame.\\\" said his \\r\\nfriend , \\\"How did it happen?\\\" \\r\\n\\r\\n\\\"She wouldn't eat the \\r\\nmushrooms!\\\"\"}, {\"ID\":\"7\",\"Category\":\"Funny jokes - 50 best jokes\",\"Joke\":\"What's the speed limit of \\r\\nsex?\\r\\n68; at 69 you have to turn around.\"}, {\"ID\":\"8\",\"Category\":\"Funny jokes - 50 best jokes\",\"Joke\":\"What did the egg say to the \\r\\n\\r\\nboiling water?\\r\\n\\\"How can you expect me to get hard so fast? I just got \\r\\nlaid a minute \\r\\nago.\\\"\"}]}"; //, {\"ID\":\"9\",\"Category\":\"Computer jokes\",\"Joke\":\"A ragged individual stranded for several months \\r\\n\\r\\non a small desert island in the middle of the Pacific Ocean one day \\r\\n\\r\\nnoticed a bottle lying in the sand with a piece of paper in it. \\r\\nRushing to \\r\\nthe bottle, he pulled out the cork and with shaking hands \\r\\nwithdrew the \\r\\nmessage. \\r\\n\\r\\n\\\"Due to lack of maintenance,\\\" he read, \\r\\n\\\"we regretfully have found it \\r\\nnecessary to cancel your e-mail \\r\\naccount.\\\"\"}, {\"ID\":\"10\",\"Category\":\"Computer jokes\",\"Joke\":\"This customer comes into \\r\\nthe computer \\r\\nstore. \\\"I'm looking for a mystery Adventure Game with \\r\\nlots of graphics. \\r\\nYou know, something really challenging.\\\" \\r\\n\\r\\n\\\"Well,\\\" replied the \\r\\nclerk, \\\"Have you tried Windows 98?\\\"\"}, {\"ID\":\"11\",\"Category\":\"Computer jokes\",\"Joke\":\"Redmond, \\r\\nWA --Microsoft announced today \\r\\nthat the official release date for the \\r\\nnew operating system \\\"Windows \\r\\n2000\\\" will be delayed until the second \\r\\nquarter of 1901.\"}, {\"ID\":\"12\",\"Category\":\"Computer jokes\",\"Joke\":\"What do computers eat when they get hungry? \\r\\n\\r\\nChips.\"}, {\"ID\":\"13\",\"Category\":\"Computer jokes\",\"Joke\":\"What's the difference between Windows 95 and a \\r\\nvirus? \\r\\nA virus does something.\"}, {\"ID\":\"14\",\"Category\":\"Aardvark jokes\",\"Joke\":\"What is uglier than an aardvark?\\r\\nTwo \\r\\naardvarks!\"}, {\"ID\":\"15\",\"Category\":\"Aardvark jokes\",\"Joke\":\"What does the aardvark call his \\r\\ndog?\\r\\nAard-bark!\"}, {\"ID\":\"16\",\"Category\":\"Aardvark jokes\",\"Joke\":\"What is the difference between an aardvark and \\r\\na coyote?\\r\\nOne has a long smeller, the other, a loud yeller!\"}, {\"ID\":\"17\",\"Category\":\"Aardvark jokes\",\"Joke\":\"Who loves \\r\\nhamburgers, French fries, and \\r\\nants?\\r\\nRonald MacAardvark!\"}, {\"ID\":\"18\",\"Category\":\"Aardvark jokes\",\"Joke\":\"What does an aardvark keep in his \\r\\naquarium?\\r\\nAn aard-shark!\"}, {\"ID\":\"19\",\"Category\":\"Email this funny joke to a friend!\",\"Joke\":\"Q: What goes VROOM, \\r\\n\\r\\nSCREECH,VROOM, SCREECH,VROOM, SCREECH?\\r\\nA: A blonde going through a \\r\\nflashing red light.\"}";

    public JokeFactory() {
        random = new Random(); // used to read random joke
        jokes = new ArrayList<>();
        readJokesJson(); // read all jokes
    }

    // get a random joke from the list
    public String getJoke() {
        if (jokes.isEmpty()) return "No jokes available";

        int index = random.nextInt(jokes.size()); // get a random joke index
        return jokes.get(index);
    }

    // read jokes from JSON file
    // and store them in the list
    private void readJokesJson() {
//        String jsonString = readJsonFile();

        // check if there is a value
        if (jsonString == null) return;

        // check for the health of the string!
        switch (jsonString) {
            case IO_EXCEPTION:
                jokes.add("Error reading file");
                break;
            case FILE_NOT_FOUND:
                jokes.add("Jokes file not available");
                break;
            default:
                getJokesFromJsonStr(jsonString);
                break;
        }
    }

    // read JSON file and return JSON string
    private String readJsonFile() {

        String line; // stores file lines
        BufferedReader bufferedReader = null; // file buffer reader
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(jokeJsonFileName);

            // Always wrap FileReader in BufferedReader.
            bufferedReader = new BufferedReader(fileReader);

            StringBuilder builder = new StringBuilder();

            while((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        } catch (FileNotFoundException e) {
            return FILE_NOT_FOUND;
        } catch (IOException e) {
            return IO_EXCEPTION;
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (final IOException e) {
                    return IO_EXCEPTION;
                }
            }
        }
    }

    // parse JSON string
    private void getJokesFromJsonStr(String jsonStr)
            throws JSONException {
        JSONObject jokesJson = new JSONObject(jsonStr);

        JSONArray jokesArray = jokesJson.getJSONArray("jokes");

        for (int i = 0; i < jokesArray.length(); i++) {
            JSONObject joke = jokesArray.getJSONObject(i);
            jokes.add(joke.getString("Joke"));
        }
    }
}
