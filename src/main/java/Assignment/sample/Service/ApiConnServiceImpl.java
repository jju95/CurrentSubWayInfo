package Assignment.sample.Service;

import Assignment.sample.Common.CustomException;
import Assignment.sample.Config.MyPropertiesConfig;
import Assignment.sample.Dto.RealTimeSubwayInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiConnServiceImpl implements ApiConnService {

    private final MyPropertiesConfig properties;

    private String apiUrl;
    private String dataType;
    private String name;
    private String key;

    @PostConstruct
    void init(){
        apiUrl = properties.getUrl();
        dataType = properties.getDataType();
        name = properties.getName();
        key = properties.getKey().getDev();
    }

    @Override
    public Map<String, Object> doService1(String subWayNm) throws IOException, InterruptedException{
        Map<String, Object> result = new HashMap<>();
        String url = makeUrl(subWayNm);
        HttpResponse<String> apiResult = getConnection(url); // httpConn
        RealTimeSubwayInfo result1 = getResult(apiResult);

        result.put("code", result1.getErrorMessage().getCode());
        result.put("errorMessage", result1.getErrorMessage());
        result.put("realtimePositionList", result1.getRealtimePositionList());

        return result;
    }

    @Override
    public RealTimeSubwayInfo doService(String subWayNm){
        if(chkForTime()){
            String url = makeUrl(subWayNm);
            HttpResponse<String> apiResult = getConnection(url); // httpConn
            return getResult(apiResult);
        }else
            throw new CustomException(" 현재 운영중인 열차가 없음 ( 열차 운행시간이 아님 ) ");
    }

    String makeUrl(String subWayNm){
        StringBuilder urlBuilder = new StringBuilder(apiUrl); /*URL*/

        try{
            urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" +  URLEncoder.encode(dataType,"UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode(name,"UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
            urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자 고정 값)*/ // 고정 값
            urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/ // 고정 값
            urlBuilder.append("/"+URLEncoder.encode(subWayNm,"UTF-8")); /* 호선 */

            log.info(urlBuilder.toString());

        }catch(UnsupportedEncodingException encErr){
            log.error("errorMsg={}", encErr);
        }

        return urlBuilder.toString();
    }

    HttpResponse<String> getConnection(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try{

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        }catch (IOException ioe){
            log.error("errorMsg={}", ioe);
        }catch (InterruptedException iterr){
            log.error("errorMsg={}", iterr);
        }

        return response;
    }

    RealTimeSubwayInfo getResult (@NonNull HttpResponse<String> response)  {
        ObjectMapper objectMapper = new ObjectMapper();
        RealTimeSubwayInfo info = null;

        try{
             info = objectMapper.readValue(response.body().toString(), RealTimeSubwayInfo.class);
        }catch (JsonProcessingException jsonParserErr){
            log.error("errorMessage={}", jsonParserErr);
        }

        return info;
    }

    // 막차시간 api
    public boolean chkForTime(){ // 임의로 정한 첫차 시간 06시 , 23시 막차
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        if((now.getHour() > 6)&&(now.getHour() <= 23)){
            return true;
        }
        return false;
    }

}
