function startPark(){
    ajaxReq("startAutoPark");
}
function forward(){
    ajaxReq("forward");
}
function stop(){
    ajaxReq("stop");
}
function turnLeft(){
    ajaxReq("turnLeft");
}
function clearAll(){
    ajaxReq("clear");
}
function fellow_magnetic(){
    ajaxReq("fellowMagnetic");
}
function startByPlatform(){
    ajaxReq("startByPlatform");
}
function previous_page(){
    $.ajax("previousPage", {
        type: "GET",
        dataType:"html",
        success: function (data) {
            console.log(data);
            window.location.href =data;
        },
        error: function () {
            console.log("error2");
        },

    });
}

function ajaxReq(action){
    $.ajax(action, {
        type: "GET",
        data:"json",
        success: function () {
            console.log(action);
        },
        error: function () {
            console.log("error2");
        }

    });
}
var mapID;
function selectMap(e)
{
    mapID=e;
    $.ajax("getMap", {
        type: "GET",
        dataType:"json",
        data: {mapId:e},
        success: function (data) {
            console.log(data);
            var map;
            map = eval(data);//parse json to object  ==val 解析json==
            var json = JSON.stringify(data);
            console.log("map.points.length:"+map.points.length);
            //console.log(json);
            var point;
            for ( var i=0;i<map.points.length;i=i+2){
                //console.log("point.x:"+map.points[i].x+"; point.y:"+map.points[i].y);
                putDateCanvas(canvasData,map.points[i].x,map.points[i].y, 0, 0,0, 255);
            }
            updateInterval();//加载完地图就开始轮询位置

        },
        error: function () {
            console.log("error2");
        },

    });

}


function startAuto(){
    var trip=JSON.stringify({"startPoint":startGPS,"endPoint":endGPS,"mapID":mapID,"carId":1});

    $.ajax("createTrip", {
        type: "POST",
        contentType : "application/json;charset=UTF-8",
        dataType:"json",
        data:JSON.stringify(trip),
        success: function (data) {
            console.log("suc");
            var path = eval(data);//parse json to object  ==val 解析json==
            console.log(JSON.stringify(path));
            // ctx.putImageData(canvasData, coordinate.x, coordinate.y);
            var list=path.points;
            for (var i in list) {
                var coordinate=list[i];
                putDateCanvas(canvasData,coordinate.x,coordinate.y,0,255,0,255);
            }

        },
        error: function () {
            console.log("error:"+trip);
        },

    });
}
// mouse event
//
var clickTime=0;
var startGPS;
var endGPS;
function doMouseDown(event) {
    var x = event.pageX;
    var y = event.pageY;
    var canvas = event.target;
    var loc = getPointOnCanvas(canvas, x, y);

    putDateCanvas(canvasData,loc.x,loc.y,0,255,0,255);
    if(clickTime%2==0){
        document.getElementById("startPlace").innerHTML = "x:"+loc.x+" y:"+loc.y;
        startGPS=loc;
    }else {
        document.getElementById("endPlace").innerHTML = "x:"+loc.x+" y:"+loc.y;
        endGPS=loc;
    }
    clickTime++;


}
function getPointOnCanvas(canvas, x, y) {
    return{ x: x- canvas.offsetLeft,
        y: y - canvas.offsetTop
    };
}

var intervalUpdatePosition;
function updateInterval(){
    intervalUpdatePosition=setInterval(function() {//后面每次更新数据
            getCurrentPosition();
        },
        1000);
}
function getCurrentPosition() {
    $.ajax("getCurrentPosition", {
        type: "GET",
        dataType: "json",
        success: function (data) {
            var coordinate = eval(data);//parse json to object  ==val 解析json==
            console.log(JSON.stringify(data));
            // ctx.putImageData(canvasData, coordinate.x, coordinate.y);
            ctx.fillStyle = "rgba("+255+","+0+","+0+","+255+")";
            ctx.fillRect( coordinate.x, coordinate.y,2,2 );
        },
        error: function () {
            clearInterval(intervalUpdatePosition);//cannot get data, stop read
            console.log("update");
            /*  marker.center=[${carInfo.gpsLongitude},${carInfo.gpsLattude}];*/
        }

    });
}
//=======================draw map==============================

//canvas2
var canvas  ;
var canvasWidth ;
var canvasHeight ;
var ctx ;
var canvasData ;

$(document).ready(function () {
    //canvas2
    canvas = document.getElementById("glcanvas2");
    canvasWidth = canvas.width;
    canvasHeight = canvas.height;
    ctx=canvas.getContext("2d");
    canvasData = ctx.createImageData(2,2);
    //canvasData = ctx.getImageData(0, 0, canvasWidth, canvasHeight);
    console.log("load");
    canvas.addEventListener("mousedown", doMouseDown, false);

});
// That's how you define the value of a pixel //
function putDateCanvas(canData,x,y,r, g, b, a) {
    for (var i=0;i<canData.data.length;i+=4)
    {
        canData.data[i+0]=r;
        canData.data[i+1]=g;
        canData.data[i+2]=b;
        canData.data[i+3]=a;
    }
    ctx.putImageData(canData, x, y);
}