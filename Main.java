package jsonParser;

import org.json.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
/**
 * convert open trivia database (json) questions to RedBot format
 * @author Nekuin
 *
 */
public class Main {
	public static void main(String[] args) {

		// find files
		File input = new File("./src/jsonParser/input.txt");
		File output = new File("./src/jsonParser/output.txt");

		// create "instance variables"
		BufferedReader reader = null;
		String wholeFile = null;
		String formatted = "";

		// read input into buffer
		try {
			reader = new BufferedReader(new FileReader(input));
			wholeFile = reader.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

		// create JSON object
		JSONObject questions = new JSONObject(wholeFile);
		// get question count
		int length = questions.getJSONArray("results").length();

		// loop through all the questions
		for (int i = 0; i < length; i++) {

			// get the category, question and answer respectively in index i
			JSONObject category = questions.getJSONArray("results").getJSONObject(i);
			JSONObject question = questions.getJSONArray("results").getJSONObject(i);
			JSONObject answer = questions.getJSONArray("results").getJSONObject(i);

			/*
			 * System.out.println(category.get("category"));
			 * System.out.println(question.get("question"));
			 * System.out.println(answer.get("correct_answer"));
			 */

			// build string
			formatted += category.get("category") + ": " + question.get("question") + "`" + answer.get("correct_answer")
					+ "\n";

			// review formatted string
			System.out.println("formatted:\n" + formatted);
		}
		// write formatted string to file
		try {
			PrintWriter writer = new PrintWriter(output, "UTF-8");
			writer.println(formatted);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
