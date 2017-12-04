# 用jsp+servlet+ajax做的一个网页的聊天室, 还有很多bug, 以后有心情再解决吧..

IDE: NetBeans NetBeans IDE 8.2
文件说明:
ajax.jsp包括了需要定时刷新请求的数据, 格式:
```
<response>
		<message> 昵称: 消息 </message>
		….
		<user>昵称</user>
		….
		<onlinenumber> (int)在线人数</onlinenumber>
</response>
```
- chatRoom.jsp是聊天室的主页面
- SessionListener.java是一个session监听器, 用来动态修改在线人数和用户列表
- loginCheck是一个昵称检查, 如果昵称重复/未填写则不允许进入聊天室页面
- sessionAndNickname是一个class包括了session和nickname的结构, 将一个昵称和一个session绑定
- send是一个发送消息的servlet, 将发送的消息插入到消息记录里

# tips: 以后继续完善
