package com.example.chatroom.dao;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomDao {

    /**
     * 聊天室列表
     */
    private Map<String, Set<String>> room = new HashMap<>();

    /**
     * 获取聊天室
     * @return
     */
    public Map<String, Set<String>> getRoom(){
        return room;
    }

    /**
     * 新增聊天室用户
     * @param roomId
     * @param userId
     */
    public void setRoomUser(String roomId, String userId) {
        if (room.get(roomId) == null) {
            Set<String> user = new HashSet<>();
            user.add(userId);
            room.put(roomId, user);
        } else {
            Set<String> user = room.get(roomId);
            user.add(userId);
        }
    }

    /**
     * 删除聊天室用户
     * @param roomId
     * @param userId
     */
    public void removeRoomUser(String roomId, String userId) {
        if (room.get(roomId) != null) {
            Set<String> user = room.get(roomId);
            user.remove(userId);
        }
    }

}
