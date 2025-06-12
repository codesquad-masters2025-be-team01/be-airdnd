package com.dmz.airdnd.common.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.airdnd.common.exception.DuplicateResourceException;
import com.dmz.airdnd.common.exception.ErrorCode;
import com.dmz.airdnd.user.domain.User;
import com.dmz.airdnd.user.dto.request.UserRequest;
import com.dmz.airdnd.user.mapper.UserMapper;
import com.dmz.airdnd.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private UserRepository userRepository;

	@Transactional
	public User signup(UserRequest userRequest) {
		if (userRepository.existsByLoginId(userRequest.getLoginId())) {
			throw new DuplicateResourceException(ErrorCode.DUPLICATE_LOGIN_ID);
		}
		if (userRepository.existsByEmail(userRequest.getEmail())) {
			throw new DuplicateResourceException(ErrorCode.DUPLICATE_EMAIL);
		}
		if (userRepository.existsByPhone(userRequest.getPhone())) {
			throw new DuplicateResourceException(ErrorCode.DUPLICATE_PHONE);
		}

		User user = UserMapper.toEntity(userRequest);

		return userRepository.save(user);
	}
}
