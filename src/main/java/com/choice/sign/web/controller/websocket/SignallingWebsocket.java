package com.choice.sign.web.controller.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 作为webrtc信令
 * Created by duhuo on 2017/10/19 0019.
 */
@ServerEndpoint("/video/{roomId}/{userId}")
@Component
public class SignallingWebsocket {

    /**
     * 存放房间与用户
     */
    private static HashMap<String,Set<User>> usersRoom = new HashMap<String,Set<User>>();
    private String roomId;
    private String userId;
    /**
     * 打开websocket
     * @param session websocket的session
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId")String roomId, @PathParam("userId")String userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    /**
     * websocket关闭
     * @param session 关闭的session
     */
    @OnClose
    public void onClose(Session session, @PathParam("roomId")String roomId, @PathParam("userId")String userId) {
        Set<User> users = usersRoom.get(this.roomId);
        User u = null;
        if(users!=null){
            try{
                for (User user:users) {
                    if(!user.getId().equals(this.userId)){
                        sendMessage(user.getSession(), "bye");//退出之后,发送给另一个人信息,以便让他断开视频连接
                    }else{
                        u = user;
                    }
                }
            }catch (Exception e){
                if(u != null){
                    users.remove(u);
                }
            }
            if(u != null){
                users.remove(u);
            }
            if(users.size() == 0){
                usersRoom.remove(this.roomId);
            }
        }
    }

    /**
     * 收到消息
     * @param message 消息内容
     * @param session 发送消息的session
     */
    @OnMessage
    public void onMessage(String message,Session session, @PathParam("roomId")String roomId, @PathParam("userId")String userId) {
        //userRoom 为singleid,singleid目前房间里面只支持2个人会诊
        Set<User> users = usersRoom.get(roomId);
        //Set<User> users = usersRoom.get(roomId);
        if(users!=null){
            User recipient = null;
            for (User user:users) {//查询当前会议中另一个在线人
                if(!user.getId().equals(userId)){
                    recipient = user;
                }
            }
            if(message.startsWith("query-On-line")){//如果查询是否有人在线
                users.add(createUser(userId,session));
                if(users.size()>0){
                    sendMessage(session,"query-On-line-Yes");
                }else{
                    sendMessage(session,"query-On-line-No");
                }
            }else if(recipient!=null){
                sendMessage(recipient.getSession(), message);
            }
        }
        if(users==null&&message.startsWith("query-On-line")){
            //如果为空,创建一个新的会诊间
            users = new HashSet<User>();
            users.add(createUser(userId,session));
            usersRoom.put(roomId,users);
            sendMessage(session,"query-On-line-No");
        }
    }
    public User createUser(String userId,Session session){
        User user = new User();
        user.setId(userId);
        user.setSession(session);
        return user;
    }
    /**
     * 给客户端发送消息
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message){
        try {
            if(session.isOpen()) {
                session.getBasicRemote().sendText(new String(message));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
