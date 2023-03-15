package com.example.chatroom.service.impl;

import com.example.chatroom.dao.ChatRoomDao;
import com.example.chatroom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomDao chatRoomDao;

    /**
     * 加入聊天室
     * @param roomId 聊天室id
     * @param userId 用户id
     * @return
     */
    @Override
    public String joinRoom(String roomId, String userId) {
        if (!StringUtils.hasLength(roomId)) {
            return "聊天室id不能为空";
        }
        if (!StringUtils.hasLength(userId)) {
            return "用户id不能为空";
        }
        Map<String, Set<String>> room = chatRoomDao.getRoom();
        if (room.get(roomId) == null) {
            chatRoomDao.setRoomUser(roomId, userId);
            return "用户：" + userId + ",加入聊天室：" + roomId + "。除当前用户外，该聊天室无其他成员";
        } else {
            Set<String> user = room.get(roomId);
            Iterator<String> iterator = user.iterator();
            StringBuilder sb = new StringBuilder();
            sb.append("除新加入用户外，该聊天室其他成员为：");
            while (iterator.hasNext()) {
                sb.append(iterator.next()).append("  ");
            }
            String result;
            if (user.contains(userId)) {
                result = "用户：" + userId + ",已加入聊天室：" + roomId + "，无需再次加入。";
            }else {
                chatRoomDao.setRoomUser(roomId, userId);
                result = "用户：" + userId + ",加入聊天室：" + roomId + "成功。";
            }
            return result + sb;
        }
    }

    /**
     * 退出聊天室
     * @param roomId 聊天室id
     * @param userId 用户id
     * @return
     */
    @Override
    public String leaveRoom(String roomId, String userId) {
        if (!StringUtils.hasLength(roomId)) {
            return "聊天室id不能为空";
        }
        if (!StringUtils.hasLength(userId)) {
            return "用户id不能为空";
        }
        Map<String, Set<String>> room = chatRoomDao.getRoom();
        if (room.get(roomId) == null) {
            return "聊天室：" + roomId + "不存在。";
        } else {
            Set<String> user = room.get(roomId);
            String result;
            if (user.contains(userId)) {
                chatRoomDao.removeRoomUser(roomId, userId);
                result = "用户：" + userId + ",退出聊天室：" + roomId + "成功。";
            } else {
                result = "用户：" + userId + ",不存在聊天室：" + roomId + "中。";
            }
            Iterator<String> iterator = user.iterator();
            StringBuilder sb = new StringBuilder();
            sb.append("该聊天室剩余成员为：");
            while (iterator.hasNext()) {
                sb.append(iterator.next()).append("  ");
            }
            return result + sb;
        }
    }
}
