<%@include file="/libs/foundation/global.jsp"%>
<cq:includeClientLib categories="jquerysamples" />
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Adobe Experience Manager Custom Carousel Demo</title>

<script type="text/javascript">

$(function(){
var playSpeed = <%=properties.get("./playSpeed",6000)%>;
var transTime = <%=properties.get("./transTime", 1500)%>;
console.log("Play speed: " + playSpeed + " Trans time: " + transTime);
    $('#carousel').infiniteCarousel({
        displayTime: playSpeed,
        transitionSpeed : transTime,
        textholderHeight : .25
    });
});

var images = <%=properties.get("./images","")%>;

console.log("Images: " + images.asset);

</script>
<style type="text/css">
body {
    padding-top: 50px;
}
#carousel {
    margin: 0 auto;
    width: 400px;
    height: 390px;
    padding: 0;
    overflow: scroll;
    border: 2px solid #999;
}
#carousel ul {
    list-style: none;
    width: 1500px;
    margin: 0;
    padding: 0;
    position: relative;
}
#carousel li {
    display: inline;
    float: left;
}
.textholder {
    text-align: left;
    font-size: small;
    padding: 6px;
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-top-left-radius: 6px;
    -webkit-border-top-right-radius: 6px;
}
</style>
</head>

<body>

<div id="carousel" >
    <ul>
        <li><img alt="" src="/content/dam/geometrixx/travel/airport_gate.jpg" width="500" height="213" /><p>Image 1.</p>
        </li>
        <li><img alt="" src="/content/dam/geometrixx/travel/airport_escalator_3..jpg" width="500" height="213" /><p>Image 2</p>
        </li>
         <li><img alt="" src="/content/dam/geometrixx/travel/subway_train.jpg" width="500" height="213" /><p>Image 3</p>
        </li>
        <li><img alt="" src="/content/dam/geometrixx/travel/subway_platform.jpg" width="500" height="213" /><p>Image 4</p>
        </li>
         <li><img alt="" src="/content/dam/geometrixx/travel/take_off.jpg" width="500" height="213" /><p>Image 5</p>
        </li>
    </ul>
</div>
<h3>This is an AEM example that uses five images retrieved from the AEM DAM and displayed within a custom Carousel component.</h3>

</body>
</html>