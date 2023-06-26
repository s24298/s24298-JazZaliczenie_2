package pl.pjatk.jazs24298nbp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class NBPService {

    public final RestTemplateConfig restTemplateConfig;

    public NBPService(RestTemplateConfig restTemplateConfig) {
        this.restTemplateConfig = restTemplateConfig;
    }


    public ArrayList<GoldInfo> getGoldInDateGold(String start, String end) {
        String URL = "http://api.nbp.pl/api/cenyzlota/"+ start + "/" + end + "/";
        ResponseEntity<String> response = restTemplateConfig.getRestTemplate().getForEntity(URL, String.class);
        String json = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<GoldInfo> goldInfoList;
        try {
            goldInfoList = objectMapper.readValue(json, new TypeReference<ArrayList<GoldInfo>>() {});
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
        return goldInfoList;
    }



}
