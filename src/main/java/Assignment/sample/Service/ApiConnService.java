package Assignment.sample.Service;

import Assignment.sample.Dto.RealTimeSubwayInfo;

import java.io.IOException;
import java.util.Map;

public interface ApiConnService {

    RealTimeSubwayInfo doService(String subWayNm) throws IOException, InterruptedException;
    Map<String, Object> doService1(String subWayNm) throws IOException, InterruptedException;


}
