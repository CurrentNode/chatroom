/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage;

/**
 *
 * @author Administrator
 */
public class sessionAndNickname {
        private String nickName;
        private String session;
        public sessionAndNickname() {
            nickName = null;
            session = null;
        }
        public String getNickNameString(){
            return nickName;
        }
        public String getSessionString(){
            return session;
        }
        public sessionAndNickname(String nickName, String session) {
            this.nickName = nickName;
            this.session = session;
        }
}
