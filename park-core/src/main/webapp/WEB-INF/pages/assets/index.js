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
function clear(){
    ajaxReq("clear");
}
function fellow_magnetic(){
    ajaxReq("fellowMagnetic");
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

/*绘制地图*/
var Ax=10;
var AY=20;
var lineStart=20;
var lineX=645;

var y1=95;
var y2=95;
var spot=85;
var spotX1=356;
var spotX2=214;
var dot1x=spotX2;
var dot1y=AY+y1+y2;
var dot2x=spotX2+y2;
var dot2y=AY+y1;
var dot3x=lineStart+lineX;
var dot3y=y1+lineStart;
var dot4x=spotX1+y1;
var dot4y=AY+y1;
var dot5x=spotX1;
var dot5y=AY;
var dot6x=spotX1-y1;
var dot6y=AY+y1;
var dot7x=Ax;
var dot7y=AY+y1;
var dot8x=spotX2-y2;
var dot8y=AY+y1;
var dot9x=dot1x;
var dot9y=dot1y;

function map(){
    var c=document.getElementById("glcanvas");
    var cxt=c.getContext("2d");

    cxt.font = "12px serif";
    cxt.fillText("A", 10, 10);
    cxt.fillText("B", 810, 10);
    cxt.fillText("C", 20, 115);
    cxt.fillText("D", 665, 115);
    cxt.fillText("E", 20, 190);
    cxt.fillText("F", 800, 190);
    cxt.fillText("起点", 250, 190);
    cxt.fillText("终点", 340, 10);
    cxt.strokeRect(Ax,AY,800,y1+y2);//最大的地图边界
    cxt.moveTo(lineStart,lineStart+y1);
    cxt.lineTo(lineStart+lineX,y1+lineStart);//磁条
    cxt.stroke();//按照绘制路径顺序连接各个坐标点
    cxt.strokeRect(spotX1-40,0,spot,20);//终点磁条
    cxt.strokeRect(spotX2-40,AY+y1+y2,spot,20);//起点磁条
    //3 转弯弧线
    cxt.beginPath();
    cxt.arc(spotX1-y1, AY,y1,Math.PI/180*90,0,true);
    //cxt.moveTo(spotX1-y1, AY);
    cxt.stroke();
    cxt.closePath();////如果先关闭绘制路径。注意，此时将会使用直线连接当前端点和起始端点。

    //2
    cxt.beginPath();
    cxt.arc(spotX1+y1, AY,y1,Math.PI/180*180,Math.PI/180*90,true);
    //cxt.moveTo(spotX1-y1, AY);
    cxt.stroke();
    cxt.closePath();

    //4
    cxt.beginPath();
    cxt.arc(spotX2-y2, AY+y1+y2,y2,0,Math.PI/180*270,true);
    //cxt.moveTo(spotX2, AY+y1+y2);
    cxt.stroke();
    cxt.closePath();

    //1
    cxt.beginPath();
    cxt.arc(spotX2+y2, AY+y1+y2,y2,Math.PI/180*270,Math.PI,true);
    //cxt.moveTo(spotX2, AY+y1+y2);
    cxt.stroke();
    cxt.closePath();

}

function addText(tx,x,y){
    var c=document.getElementById("glcanvas");
    var cxt=c.getContext("2d");
    cxt.font = "12px serif";
    cxt.fillText(tx, x, y);
}
var oldballx=0;
var oldbally=0;
var ball=null;
/* function updateBall( x, y){
 var c2=document.getElementById("glcanvas2");
 var cxt2=c2.getContext("2d");
 cxt2.globalCompositeOperation=source-over;
 console.log("oldballx:"+oldballx+"  "+"oldbally");
 ball = {
 x: x,
 y: y,
 radius: 10,
 color: 'red',
 draw: function() {
 cxt2.beginPath();
 cxt2.arc(this.x, this.y, this.radius, 0, Math.PI*2, true);
 cxt2.closePath();
 cxt2.fillStyle = this.color;
 cxt2.fill();
 }
 };
 oldballx=x-10;
 oldbally=y-10;
 console.log("x:"+x+"  "+"y:"+y);

 ball.draw();
 } */
function updateBall( x, y){
    var c=document.getElementById("glcanvas");
    var cxt=c.getContext("2d");
    console.log("oldballx:"+oldballx+"  "+"oldbally");
    cxt.clearRect(oldballx,oldbally, 10, 10);
    ball = {
        x: x,
        y: y,
        radius: 5,
        color: 'red',
        draw: function() {
            cxt.beginPath();
            cxt.arc(this.x, this.y, this.radius, 0, Math.PI*2, true);
            cxt.closePath();
            cxt.fillStyle = this.color;
            cxt.fill();
        }
    };
    oldballx=x-5;
    oldbally=y-5;
    console.log("x:"+x+"  "+"y:"+y);

    ball.draw();
}

var intervalUpdate;
updateInterval();//页面启动就开始轮询更新marker
function updateInterval(){
    intervalUpdate=setInterval(function() {//后面每次更新marker gps数据
            update();
        },
        1000);
}
var oldSeq=-1;
function update(){
    $.ajax("update", {
        type: "GET",
        dataType: "json",
        success: function (data) {
            var msg = eval(data);//parse json to object  ==val 解析json==
            /*updateBall(dot2x,dot2y);*/
            if(oldSeq!=msg.message){
                oldSeq=msg.message;
                console.log(msg.message);
                if(msg.message==1){updateBall(dot1x,dot1y);addText('1',dot1x-10,dot1y-10);}
                if(msg.message==2){updateBall(dot1x+7,dot1y-30);addText('2',dot1x,dot1y-40);}
                if(msg.message==3){updateBall(dot2x,dot2y);addText('3',dot2x-10,dot2y-10);}
                if(msg.message==4){updateBall(dot3x,dot3y);addText('4',dot3x-10,dot3y-10);}
                if(msg.message==5){updateBall(dot3x,dot3y);addText('5',dot3x+10,dot3y-10);}
                if(msg.message==6){updateBall(dot5x,dot5y);addText('6',dot5x-15,dot5y+10);}
                if(msg.message==7){updateBall(dot5x-7,dot5y+30);addText('7',dot5x-17,dot5y+40);}
                if(msg.message==8){updateBall(dot5x-7,dot5y+30);addText('8',dot5x+3,dot5y+40);}
                if(msg.message==9){updateBall(dot6x,dot6y);addText('9',dot6x-15,dot6y+10);}
                if(msg.message==10){updateBall(dot7x,dot7y);addText('10',dot7x-10,dot7y-10);}
                if(msg.message==11){updateBall(dot7x,dot7y);addText('11',dot7x+10,dot7y-10);}

                if(msg.message==12){updateBall(dot9x,dot9y);addText('12',dot9x-15,dot9y+10);}
                if(msg.message==13){updateBall(dot9x,dot9y);addText('13',dot9x+10,dot9y+10);}
            }

        },
        error: function () {
            clearInterval(intervalUpdate);//cannot get data, stop read
            console.log("update");
            /*  marker.center=[${carInfo.gpsLongitude},${carInfo.gpsLattude}];*/
        }

    });
}