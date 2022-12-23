let subwayNm = ['1호선', '2호선', '3호선','4호선','5호선','6호선','7호선','8호선','9호선'];

$(document).ready(function() {
    let html = "";
    subwayNm.forEach((val,idx)=>{
        idx == 0
            ? html += '<option value="'+val+'" selected>'+val+'</option>'
            : html += '<option value="'+val+'">'+val+'</option>';
    })
    $("#subwayNm").html(html);
});

function getSubwayInfo(){
    let selectedSubwayNm = $('#subwayNm :selected').val();

    $.get('/subwayInfo',
        {'subWayNm' : selectedSubwayNm},
        function (data, status){
        console.log(data.get(""));
    });

}

function makeScope(data){

    console.log('1 :: '+data)
    // 대충 여기서 result값 보여주기
}

