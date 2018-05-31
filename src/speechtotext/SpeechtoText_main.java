package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechtoText_main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		SpeechToText service = new SpeechToText();
	    service.setUsernameAndPassword("J16014", "J16014");

	    File audio = new File("audio/output.wav");

	    RecognizeOptions options = null;
		try {
			options = new RecognizeOptions.Builder()
					.model("ja-JP_BroadbandModel")
			        .audio(audio)
			        .contentType(RecognizeOptions.ContentType.AUDIO_WAV)
			        .build();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	    SpeechRecognitionResults transcript = service.recognize(options).execute();

	    System.out.println(transcript);

	    String s = String.valueOf(transcript);

	    ObjectMapper mapper = new ObjectMapper();

	    try {
			JsonNode node = mapper.readTree(s);

			String transcript_ = node.get("results").get(0).get("alternatives").get(0).get("transcript").asText();
			System.out.println("transcript : " + transcript_);

			float confidence_ = node.get("results").get(0).get("alternatives").get(0).get("confidence").floatValue();
			System.out.println("confidence : " + confidence_);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
