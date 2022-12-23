package Assignment.sample.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class RealTimeSubwayInfo {

    ErrorMessageVo errorMessage;

    List<RealtimePositionListVo> realtimePositionList;

}
