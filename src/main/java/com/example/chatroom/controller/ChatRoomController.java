package com.example.chatroom.controller;

import com.example.chatroom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("chatroom")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    /**
     * 加入聊天室
     * @param roomId 聊天室id
     * @param userId 用户id
     * @return
     */
    @RequestMapping("joinRoom")
    public String joinRoom(@RequestParam(value = "roomId", defaultValue = "") String roomId,
                         @RequestParam(value = "userId", defaultValue = "") String userId){
        try {
            return chatRoomService.joinRoom(roomId, userId);
        }catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return "加入聊天室出错，请联系管理员处理。";
        }

    }

    /**
     * 退出聊天室
     * @param roomId 聊天室id
     * @param userId 用户id
     * @return
     */
    @RequestMapping("leaveRoom")
    public String leaveRoom(@RequestParam(value = "roomId", defaultValue = "") String roomId,
                          @RequestParam(value = "userId", defaultValue = "") String userId){
        try {
            return chatRoomService.leaveRoom(roomId, userId);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "退出聊天室出错，请联系管理员处理。";
        }
    }

}
