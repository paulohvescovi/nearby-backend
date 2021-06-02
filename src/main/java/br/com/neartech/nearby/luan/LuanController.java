package br.com.neartech.nearby.luan;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/public/luan")
public class LuanController {

    private static String URL = "https://www.copel.com/arcgis/rest/services/compartilhamentoPS/compartUsuPS/MapServer/0/query";

    RestTemplate restTemplate = new RestTemplate();

    private static final String SAMPLE_CSV_FILE = "./luan.csv";

    @GetMapping
    public ResponseEntity<List<FeatureApi>> testeCopel(){
        List<FeatureApi> allValues = new ArrayList<>();

        BufferedWriter writer = null;
        CSVPrinter csvPrinter = null;
        try {
            writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("NUM_SEQ_GEO", "Situacao", "LONGITUDE", "LATITUDE"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean existsValue = true;
        Integer idInicio = 1;
        do {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    URL, obtemRequest(idInicio, idInicio + 750) , String.class
            );
            List<FeatureApi> responseList = obtemFeatureListByJson(response);
            if (responseList.isEmpty()){
                existsValue = false;
            }

            for (FeatureApi featureApi : responseList) {
                if (featureApi == null || featureApi.getAttributes() == null){
                    continue;
                }
                try {
                    csvPrinter.printRecord(
                            featureApi.getAttributes().getNUM_SEQ_GEO(),
                            featureApi.getAttributes().getSITUACAO(),
                            featureApi.getAttributes().getLATITUDE(),
                            featureApi.getAttributes().getLONGITUDE());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Tamanho da lista ........... " + idInicio);
            idInicio+=750;
        } while(existsValue);

        System.out.println("Terminou o processo com " + idInicio + "registros");

        try {
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(allValues.subList(0, 10));//retorna soh 10
    }

    private List<FeatureApi> obtemFeatureListByJson(ResponseEntity<String> response) {
        String jsonProcessado = new Gson().toJson(response.getBody()).replace("\\n", "").replace("\\", "");
        jsonProcessado = jsonProcessado.substring(1, jsonProcessado.length()-1);

        FeatureApi[] features = new Gson().fromJson(new Gson().fromJson(jsonProcessado, JsonObject.class).get("features"), FeatureApi[].class);
        if (features == null){
            return new ArrayList<>();
        }
        return Arrays.asList(features);
    }

    private String obtemIds(int numeroInicial, int numeroFinal) {
        StringBuilder objectsIds = new StringBuilder();
        for (int i = numeroInicial; i <=numeroFinal ; i++) {
            objectsIds.append(i).append(",");
        }
        return objectsIds.substring(0, objectsIds.length()-1);
    }

    private HttpEntity<MultiValueMap<String, String>> obtemRequest(int numeroInicial, int numeroFinal){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();

        map.add("outFields", "OBJECTID,NUM_SEQ_GEO,SITUACAO,LONGITUDE,LATITUDE");
        map.add("f", "pjson");
        map.add("objectIds", obtemIds(numeroInicial, numeroFinal));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        return request;
    }

}
