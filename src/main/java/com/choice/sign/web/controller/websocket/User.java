package com.choice.sign.web.controller.websocket;

import javax.websocket.Session;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
public class User {

        private String id;

        private Session session;

        public String getId() {

            return id;
        }

        public void setId(String id) {

            this.id = id;
        }

        public Session getSession() {

            return session;
        }

        public void setSession(Session session) {

            this.session = session;
        }
}
