package com.dmz.airdnd.common.auth;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dmz.airdnd.user.domain.Role;
import com.dmz.airdnd.user.domain.User;
import com.dmz.airdnd.user.dto.request.response.UserRequest;
import com.dmz.airdnd.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

	@InjectMocks
	private AuthService authService;

	@Mock
	private UserRepository userRepository;

	@Test
	@DisplayName("중복된 유저가 없으면 회원가입을 성공한다.")
	void testSignup() {
		//given
		User user = createTestUser();
		UserRequest userRequest = createUserRequestFrom(user);
		when(userRepository.existsByLoginId(anyString())).thenReturn(false);
		when(userRepository.existsByEmail(anyString())).thenReturn(false);
		when(userRepository.existsByPhone(anyString())).thenReturn(false);
		when(userRepository.save(any())).thenReturn(user);
		//when
		User newUser = authService.signup(userRequest);
		//then
		verify(userRepository).existsByLoginId(user.getLoginId());
		verify(userRepository).existsByEmail(user.getEmail());
		verify(userRepository).existsByPhone(user.getPhone());
		assertThat(newUser.getLoginId()).isEqualTo(user.getLoginId());
		assertThat(newUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(newUser.getPhone()).isEqualTo(user.getPhone());
		assertThat(newUser.getRole()).isEqualTo(Role.USER);
	}

	private User createTestUser() {
		return User.builder()
			.id(1L)
			.loginId("testUser")
			.password("password123")
			.email("test@test.com")
			.role(Role.USER)
			.phone("010-1234-5678")
			.build();
	}

	private UserRequest createUserRequestFrom(User user) {
		return UserRequest.builder()
			.loginId(user.getLoginId())
			.password(user.getPassword())
			.email(user.getEmail())
			.phone(user.getPhone())
			.build();
	}

}
