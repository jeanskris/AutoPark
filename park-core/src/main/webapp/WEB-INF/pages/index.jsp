<%--
  Created by IntelliJ IDEA.
  User: ZJDX
  Date: 2016/6/20
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<!-- MAIN EFFECT -->
<script type="text/javascript" src="assets/jquery.js"></script>
<script type="text/javascript" src="assets/jhere-custom.js"></script>
<%--<script type="text/javascript" src="assets/jquery.min.js"></script>--%>
<script type="text/javascript" src="assets/bootstrap.js"></script>
<script type="text/javascript" src="assets/load.js"></script>
<!--self-defined-->
<script type="text/javascript" src="assets/index.js"></script>
<button id="start" onclick="startPark()">Start</button>
<button id="clear" onclick="clearAll()">clear</button>

<%--<button id="forward" onclick="forward()">forward</button>
<button id="turnLeft" onclick="turnLeft()">turnLeft</button>
<button id="stop" onclick="stop()">stop</button>
<button id="fellow" onclick="fellow_magnetic()">fellow_megnetic</button>--%>
<button id="previous" onclick="previous_page()">previous page</button>

<div>
    <p>select Map:</p>
    <form>
        <select name="map" onchange="selectMap(this.options[this.options.selectedIndex].value)">
            <option value="1">1</option>
            <option value="1">2</option>
            <option value="1">3</option>
            <option value="1">4</option>
        </select>
    </form>
</div>

<p id="commandTime" >0 </p>
<div>
    <div ><p>startPlace:</p><p  id="startPlace"></p></div>
    <div><p>endPlace:</p><p id="endPlace"></p></div>
    <button id="startAuto" onclick="startAuto()">startAuto</button>
</div>

<%--<canvas id="glcanvas" width="900" height="400" style="border:1px solid #c3c3c3;">
</canvas>--%>

<canvas id="glcanvas2"  width="1000" height="1000"     style="border:1px solid #c3c3c3;"  ></canvas>


</body>
</html>
