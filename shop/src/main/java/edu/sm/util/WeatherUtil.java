package edu.sm.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherUtil {

    public static Object getWeather(String loc, String key) throws IOException, ParseException {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String nowString = today.format(dateTimeFormatter) + "0600"; // 202404090600

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8)); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("10", StandardCharsets.UTF_8)); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("JSON", StandardCharsets.UTF_8)); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("stnId", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(loc, StandardCharsets.UTF_8)); /*108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(nowString, StandardCharsets.UTF_8)); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/

        return getJsonResponse(urlBuilder.toString());
    }

    public static Object getWeatherForecast(String loc, String key) throws IOException, ParseException {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String nowString = today.format(dateTimeFormatter) + "0600"; // 202404090600

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8)); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("10", StandardCharsets.UTF_8)); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("JSON", StandardCharsets.UTF_8)); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("regId", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(loc, StandardCharsets.UTF_8)); /*108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(nowString, StandardCharsets.UTF_8)); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/

        return getJsonResponse(urlBuilder.toString());
    }

    public static Object getWeather2(String loc, String key) throws IOException, ParseException {
        String sendUrl = "https://api.openweathermap.org/data/2.5/weather";
        StringBuilder urlBuilder = new StringBuilder(sendUrl); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("id", StandardCharsets.UTF_8) + "=" + loc);
        urlBuilder.append("&" + URLEncoder.encode("units", StandardCharsets.UTF_8) + "=" + "metric");
        urlBuilder.append("&" + URLEncoder.encode("lang", StandardCharsets.UTF_8) + "=" + "kr");
        urlBuilder.append("&" + URLEncoder.encode("appid", StandardCharsets.UTF_8) + "=" + key);

        return getJsonResponse(urlBuilder.toString());
    }

    public static Object getWeather2Forecast(String loc, String key) throws IOException, ParseException {
        String sendUrl = "https://api.openweathermap.org/data/2.5/forecast";
        StringBuilder urlBuilder = new StringBuilder(sendUrl); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("id", StandardCharsets.UTF_8) + "=" + loc);
        urlBuilder.append("&" + URLEncoder.encode("units", StandardCharsets.UTF_8) + "=" + "metric");
        urlBuilder.append("&" + URLEncoder.encode("lang", StandardCharsets.UTF_8) + "=" + "kr");
        urlBuilder.append("&" + URLEncoder.encode("appid", StandardCharsets.UTF_8) + "=" + key);

        return getJsonResponse(urlBuilder.toString());
    }

    private static Object getJsonResponse(String urlString) throws IOException, ParseException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(sb.toString());
    }
}

