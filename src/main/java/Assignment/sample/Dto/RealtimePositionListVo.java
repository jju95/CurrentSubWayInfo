package Assignment.sample.Dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Getter @Setter
public class RealtimePositionListVo {

    public String beginRow;
    public String endRow;
    public String curPage;
    public String pageRow;
    public Integer totalCount;
    public Integer rowNum;
    public Integer selectedCount;
    public String subwayId;
    public String subwayNm;
    public String statnId;
    public String statnNm;
    public String trainNo;
    public String lastRecptnDt;
    public String  recptnDt;
    public String updnLine;
    public String statnTid;
    public String statnTnm;
    public String trainSttus;
    public String directAt;
    public String lstcarAt;

}
