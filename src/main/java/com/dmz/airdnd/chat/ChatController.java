package com.dmz.airdnd.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.dmz.airdnd.chat.domain.ChatMessage;
import com.dmz.airdnd.chat.domain.ChatRoom;
import com.dmz.airdnd.chat.dto.ChatMessageRequest;
import com.dmz.airdnd.chat.dto.ChatMessageResponse;
import com.dmz.airdnd.chat.infra.RedisPublisher;
import com.dmz.airdnd.chat.repository.ChatMessageRepository;
import com.dmz.airdnd.chat.repository.ChatRoomRepository;
import com.dmz.airdnd.common.auth.UserContext;
import com.dmz.airdnd.user.domain.User;
import com.dmz.airdnd.user.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

	private final ChatMessageRepository chatMessageRepository;
	private final ChatRoomRepository chatRoomRepository;
	private final UserRepository userRepository;
	private final RedisPublisher redisPublisher;

	@MessageMapping("/chat.send")
	public void sendMessage(@Valid ChatMessageRequest request) {
		User user = userRepository.findById(UserContext.get().getId()).orElseThrow(
			() -> new IllegalArgumentException("User not found"));

		ChatRoom chatRoom = chatRoomRepository.findById(request.getRoomId())
			.orElseThrow(() -> new IllegalArgumentException("Chat room not found"));

		ChatMessage chatMessage = ChatMessage.builder()
			.chatRoom(chatRoom)
			.content(request.getContent())
			.sender(user)
			.build();

		ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

		redisPublisher.publish("chatroom." + request.getRoomId(), ChatMessageResponse.from(savedMessage));
	}
}
