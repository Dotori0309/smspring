package edu.sm.controller;

import edu.sm.app.dto.Content;
import edu.sm.app.dto.Marker;
import edu.sm.app.dto.Search;
import edu.sm.app.service.MarkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChartRestController {
    @RequestMapping("/chart1")
    public Object chart1() throws Exception{
        //[]
        JSONArray jsonArray = new JSONArray();

        //{}
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        jsonObject1.put("name", "Korea");
        jsonObject1.put("name", "Japan");
        jsonObject1.put("name", "China");

        //[]
        JSONArray data1Array = new JSONArray();
        JSONArray data2Array = new JSONArray();
        JSONArray data3Array = new JSONArray();
        Random random = new Random();
        for(int i=0; i<12; i++){
            data1Array.add(random.nextInt(100)+1);
            data2Array.add(random.nextInt(100)+1);
            data3Array.add(random.nextInt(100)+1);
        }
        jsonObject1.put("data", data1Array);
        jsonObject2.put("data", data2Array);
        jsonObject3.put("data", data3Array);

        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        jsonArray.add(jsonObject3);
        return jsonArray;
    }
}







