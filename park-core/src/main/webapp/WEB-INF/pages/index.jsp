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
<body onload="map()">
<!-- MAIN EFFECT -->
<script type="text/javascript" src="assets/jquery.js"></script>
<script type="text/javascript" src="assets/jhere-custom.js"></script>
<%--<script type="text/javascript" src="assets/jquery.min.js"></script>--%>
<script type="text/javascript" src="assets/bootstrap.js"></script>
<script type="text/javascript" src="assets/load.js"></script>
<!--self-defined-->
<script type="text/javascript" src="assets/index.js"></script>


<button id="forward" onclick="forward()">forward</button>
<button id="turnLeft" onclick="turnLeft()">turnLeft</button>
<button id="stop" onclick="stop()">stop</button>
<button id="start" onclick="startPark()">Start</button>
<button id="clear" onclick="clear()">clear</button>
<button id="fellow" onclick="fellow_magnetic()">fellow_megnetic</button>
<p id="commandTime" >0 </p>
<p id="result" ></p>
<canvas id="glcanvas" width="900" height="400" style="border:1px solid #c3c3c3;">
</canvas>
<canvas id="glcanvas2"  width="900" height="400"  position="static" style="border:1px solid #c3c3c3;" "z-index:2" ></canvas>

</body>
</html>