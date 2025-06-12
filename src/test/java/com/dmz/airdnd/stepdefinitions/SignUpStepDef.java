package com.dmz.airdnd.stepdefinitions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.dmz.airdnd.AbstractContainerBase;
import com.dmz.airdnd.common.auth.AuthService;
import com.dmz.airdnd.user.dto.request.UserRequest;
import com.dmz.airdnd.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
@CucumberContextConfiguration
public class SignUpStepDef extends AbstractContainerBase {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepository userRepository;

	private UserRequest request;
	private ResultActions resultActions;
	@Autowired
	private ObjectMapper objectMapper;

	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}

	@Given("유효한 회원가입 요청 정보가 준비되어 있다.")
	public void 유효한_회원가입_요청_정보_준비되어_있다() {
		request = new UserRequest(
			"user123", "pass!234", "user@example.com", "01012345678"
		);
	}

	@Given("저장소에 동일한 loginId, email, phone을 가진 유저가 존재하지 않는다.")
	public void 저장소에_동일한_유저가_존재하지_않는다() {
	}

	@When("사용자가 회원가입 API를 호출했을 때")
	public void 사용자가_회원가입_API를_호출하면() throws Exception {
		String json = objectMapper.writeValueAsString(request);
		resultActions = mockMvc.perform(post("/api/auth/signup")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json));
	}

	@Then("응답 상태로 201 Created를 받는다.")
	public void 회원가입_반환_정보_비교() throws Exception {
		resultActions.andExpect(status().isCreated());
	}

	@Then("응답 본문은 다음과 같아야 한다:")
	public void 응답_본문은_다음과_같아야_한다(DataTable dataTable) throws Exception {
		Map<String, String> expected = dataTable.asMaps().get(0);
		resultActions
			.andExpect(jsonPath("$.success").value(Boolean.parseBoolean(expected.get("success"))))
			.andExpect(jsonPath("$.data").isEmpty())
			.andExpect(jsonPath("$.error").isEmpty());
	}
}
