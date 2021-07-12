package com.core.dataprovider;

import static com.core.dataprovider.Constants.ENV.UAT;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.Scenario;

public class JsonDataProvider {

	private static final String NA = "NA";

	private JsonDataProvider() {

	}

	// Singleton Desing Pattern
	private static JsonDataProvider instance = new JsonDataProvider();

	public static JsonDataProvider getInstance() {
		return instance;
	}

	public static <T> T readRequestValuesFromJSONObject(Scenario sc, Class<T> clazz)
			throws IOException, ParseException, URISyntaxException {

		String testenv = System.getProperty("env");
		String fileNameWithOutExt = getFeatureFileName(sc.getUri());
		String datablock;

		if (testenv == null) {
			testenv = UAT;
		}

		datablock = getScenarioData(sc.getName(), "-(.*?)#");

		if (datablock != NA) {

			URI uri = null;
			uri = ClassLoader.getSystemResource("testData/" + fileNameWithOutExt + ".json").toURI();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode mainnode, envNode, dataBlockNode;

			mainnode = objectMapper.readTree(new FileReader(uri.getPath()));
			if (mainnode.isEmpty()) {
				throw new NullPointerException(
						"Specified enviorment data not availble in the Json" + fileNameWithOutExt);
			}

			envNode = mainnode.get(testenv);

			if (envNode.isEmpty()) {
				throw new NullPointerException(
						"Specified enviorment data not availble in the Json" + fileNameWithOutExt);

			}

			dataBlockNode = envNode.get(datablock);

			if (dataBlockNode.isEmpty()) {
				throw new NullPointerException("Specified data block not availble in the Json" + fileNameWithOutExt);

			}

			return objectMapper.readValue(dataBlockNode.toString(), clazz);
		} else {
			return null;
		}
	}

	/**
	 * Return the feature name without file extension
	 * 
	 * @param uri
	 * @return
	 */
	private static String getFeatureFileName(URI uri) {
		File file = new File(uri.getPath());
		String fileNameWithOutExt = FilenameUtils.removeExtension(file.getName());
		return fileNameWithOutExt;
	}

	public static String getScenarioData(String mydata, String patternexp) {
		Pattern pattern = Pattern.compile(patternexp);
		Matcher matcher = pattern.matcher(mydata);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return NA;

	}
}
