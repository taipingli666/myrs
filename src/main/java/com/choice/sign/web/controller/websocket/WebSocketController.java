package com.choice.sign.web.controller.websocket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.choice.domain.entity.external.BussConsultationMessage;
import com.choice.domain.service.external.BussConsultationSingleService;

@ServerEndpoint(value = "/ws/{id}",configurator=GetHttpSessionConfigurator.class)
@Component
public class WebSocketController {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    public static ConcurrentHashMap<Integer,WebSocketController> map = new ConcurrentHashMap<Integer,WebSocketController>();
    private Integer id;
    private Session session;
    @Autowired
    private BussConsultationSingleService bussConsultationSingleService;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("id") Integer id,Session session) {
        this.id = id;
        this.session = session;
        map.put(id,this);
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        System.out.println("增加一个连接,长度现在为"+map.size());
        //通知列表在线,暂时推送全部人,之后改成推给只拥有的人
        notifyOnline(id,"1");
    }
    /**
     * 通知列表在线的方法
     */
    public void notifyOnline(Integer id,String type){
        Map<String,Object> m = null;
        for(Integer i : map.keySet()){
            if(i .equals(this.id) ){
                continue;
            }
            try{
                m = new HashMap<String,Object>();
                m.put("type",type);
                m.put("id",id);
                WebSocketController w = map.get(i);
                w.getSession().getAsyncRemote().sendText(JSON.toJSONString(m));
            }catch (Exception e){
                //通知错误
            }
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        notifyOnline(this.id,"2");
        subOnlineCount();           //在线数减1
        map.remove(this.id);
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        System.out.println("关闭一个连接,长度现在为"+map.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session)  {

    }
    public void sendMessage(String message) throws IOException {
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 发送视频会诊邀请
     */
    public void sendVideo(String sendName,Integer sendId,Long singleId){
        //生成json
        BussConsultationMessage message = new BussConsultationMessage();
        message.setType("3");
        message.setCreaterCode(sendId);
        message.setSpare(singleId+"");
        message.setContent(sendName+"向你发起了视频会诊,发起时间为" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        this.session.getAsyncRemote().sendText(JSON.toJSONString(message));
    }

    /**
     * 发送消息,第一个参数发给谁,第二个,是发送什么
     */
    public static void sendMessage(String sendName,String message) throws IOException {

    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 发生错误时调用
     @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }
     /**
      * 群发自定义消息
      * */
    public static void sendInfo(String message) throws IOException {


    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketController.onlineCount--;
    }

    public static ConcurrentHashMap getMap(){
        return map;
    }

}
