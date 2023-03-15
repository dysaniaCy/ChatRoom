package com.example.chatroom.service;

public interface ChatRoomService {

    /**
     * 加入聊天室
     * @param roomId 聊天室id
     * @param userId 用户id
     * @return
     */
    String joinRoom(String roomId, String userId);

    /**
     * 退出聊天室
     * @param roomId 聊天室id
     * @param userId 用户id
     * @return
     */
    String leaveRoom(String roomId, String userId);

}
