package com.dmz.airdnd.common.auth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dmz.airdnd.user.domain.Role;
import com.dmz.airdnd.user.domain.User;
import com.dmz.airdnd.user.dto.request.response.UserRequest;
import com.dmz.airdnd.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private UserRepository userRepository;

	@Transactional
	public User signup(UserRequest userRequest) {
		if (userRepository.existsByLoginId(userRequest.getLoginId())) {
			throw new IllegalArgumentException("이미 존재하는 로그인 아이디입니다.");
		}
		if (userRepository.existsByEmail(userRequest.getEmail())) {
			throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
		}
		if (userRepository.existsByPhone(userRequest.getPhone())) {
			throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
		}

		User user = User.builder()
			.loginId(userRequest.getLoginId())
			.password(userRequest.getPassword())
			.email(userRequest.getEmail())
			.phone(userRequest.getPhone())
			.role(Role.USER)
			.build();

		return userRepository.save(user);
	}
}
