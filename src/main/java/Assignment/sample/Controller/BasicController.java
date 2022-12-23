package Assignment.sample.Controller;

import Assignment.sample.Dto.RealTimeSubwayInfo;
import Assignment.sample.Dto.Response;
import Assignment.sample.Service.ApiConnServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BasicController {

    private final ApiConnServiceImpl apiConnService;

    @GetMapping
    String init(){
      return "index";
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("subwayInfo")
    Response<RealTimeSubwayInfo> getSubwayInfo(@RequestParam String subWayNm) throws Exception {
        RealTimeSubwayInfo info = apiConnService.doService(subWayNm);
        return Response.success(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("subwayInfo1")
    Map<String, Object> getSubwayInfo1(@RequestParam String subWayNm) throws Exception {
        Map<String, Object> result = apiConnService.doService1(subWayNm);
        return result;
    }

}
