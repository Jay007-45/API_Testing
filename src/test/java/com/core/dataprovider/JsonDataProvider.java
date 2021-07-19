package com.core.dataprovider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.core.dataprovider.Constants.ENV.UAT;

public class JsonDataProvider {

    private static final String NA = "NA";
    private static final JsonDataProvider instance = new JsonDataProvider();
    private static String executionEnv;

    private JsonDataProvider() {
        executionEnv = System.getProperty("env");
    }

    public static JsonDataProvider getInstance() {
        return instance;
    }

    public static <T> T readRequestValuesFromJSONObject(Scenario sc, Class<T> clazz)
            throws IOException, ParseException, URISyntaxException {


        String fileNameWithOutExt = getFeatureFileName(sc.getUri());
        String dataNode;

        if (executionEnv == null) {
            executionEnv = UAT;
        }

        dataNode = getScenarioData(sc.getName(), "-(.*?)#");

        if (dataNode != NA) {

            URI uri = null;
            uri = ClassLoader.getSystemResource("testData/" + fileNameWithOutExt + ".json").toURI();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mainNode, envNode, dataBlockNode;

            mainNode = objectMapper.readTree(new FileReader(uri.getPath()));
            if (mainNode.isEmpty()) {
                throw new NullPointerException(
                        "Specified environment data not available in the Json" + fileNameWithOutExt);
            }

            envNode = mainNode.get(executionEnv);

            if (envNode.isEmpty()) {
                throw new NullPointerException(
                        "Specified environment data not available in the Json" + fileNameWithOutExt);

            }

            dataBlockNode = envNode.get(dataNode);

            if (dataBlockNode==null) {
                throw new NullPointerException("Specified data block not available in the Json" + fileNameWithOutExt);

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

    /**
     * In order to load extra data from Json
     *
     * @param filename
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T loadDataFromJsonFile(String filename, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = null;
        Path filepath = null;
        if (filename != null || !filename.isEmpty()) {

            try {
                filepath = Paths.get(ClassLoader.getSystemResource("testData/" + filename + ".json").toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return new ObjectMapper().readValue(filepath.toFile(), clazz);
    }
}
