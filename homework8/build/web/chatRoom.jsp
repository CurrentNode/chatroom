<%-- 
    Document   : chatRoom
    Created on : 2017-11-30, 15:47:31
    Author     : Administrator
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="mainPackage.sessionAndNickname"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html style="height: 100%">
    <head>
        <script type="text/javascript">
            var XMLHttpReq;
            function createXMLHttpRequest() {  
                if(window.XMLHttpRequest) { //Mozilla 浏览器  
                    XMLHttpReq = new XMLHttpRequest();  
                }  
                else if (window.ActiveXObject) { // IE浏览器  
                    try {  
                        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");  
                    } catch (e) {  
                        try {  
                            XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");  
                        } catch (e) {}  
                    }  
                }       
            }
            function sendRequest() {
                createXMLHttpRequest(); 
                var url = "ajax.jsp";  
                XMLHttpReq.open("post", url, true);  
                XMLHttpReq.onreadystatechange = processResponse;//指定响应函数  
                XMLHttpReq.send(null);  // 发送请求  
            }  
            // 处理返回信息函数  
            function processResponse() {  
                if (XMLHttpReq.readyState === 4) { // 判断对象状态  
                    if (XMLHttpReq.status === 200) { // 信息已经成功返回，开始处理信息  
                        DisplayHot();
                        setTimeout("sendRequest()", 1000);  
                    } else { //页面不正常  
                        
                    }  
                }  
            }  
            function DisplayHot() {  
                var messagesArray = XMLHttpReq.responseXML.getElementsByTagName("message");
                var temp1 = '', temp2 = '<div style="font-size: 2vw">当前在线用户:</div>';
                for(var i = 0; i < messagesArray.length; i++){
                    if(messagesArray[i].firstChild.nodeValue !== null){
                        temp1 += '<div style="font-size: 2vw">';
                        temp1 += messagesArray[i].firstChild.nodeValue;
                        temp1 += "</div>";
                    }
                }
                if(messagesArray.length !== 0)
                    document.getElementById("messages").innerHTML = temp1;
                var userArray =  XMLHttpReq.responseXML.getElementsByTagName("user");
                for(var i = 0; i < userArray.length; i++){
                    if(userArray[i].firstChild.nodeValue !== null){
                        temp2 += '<div style="font-size: 2vw">';
                        temp2 += userArray[i].firstChild.nodeValue;
                        temp2 += "</div>";
                    }
                }
                if(userArray.length !== 0)
                    document.getElementById("users").innerHTML = temp2;
                var onlineNumber = XMLHttpReq.responseXML.getElementsByTagName("onlinenumber")[0].firstChild.nodeValue;
                document.getElementById("onlinenumber").innerHTML = '当前在线人数:' + onlineNumber;
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>老年人聊天室</title>
        <%!int number;%>
    </head>
    <body style="width:100%;height: 100%;overflow: hidden;" onload="sendRequest()">
        <div style="width:100%;height:100%;">
            <div style="width: 69%; height: 100%; border: solid 1px #000; float: left;">
            	<div style="height:auto; width: 100%;">
	            <div style="margin:0 auto; width:100%;height: auto;font-size: 2vw">你的昵称：
                        <%=(String)session.getAttribute("nickname")%>
                    </div>
	            <div id="onlinenumber" style="margin:0 auto; width:100%;height: auto;font-size: 2vw;">
                    </div>
               	</div>
                <div id="chat">
                    <div id = "messages" style="height:80%;border:solid 1px #000; overflow-y:scroll" >
                    </div>
                </div>
                <div style="height: auto; width: 100%; font-size: 2vw;">
                    <form method="post" action="send">
                        <input type = "text" name = "message"  style="width:64%;float: left;height: 100%;font-size: 3vw;"/>
                        <input style="width:34%;float: left;height: 100%;font-size: 3vw;" type = "submit" value = "发送" />
                    </form>
                </div>
            </div>
            <div style="width: 29%; height: 100%; border:solid 1px #000;float: right;">
                <div id="users" style="font-size: 2vw"></div>
                
                
            </div>
        </div>
    </body>
</html>