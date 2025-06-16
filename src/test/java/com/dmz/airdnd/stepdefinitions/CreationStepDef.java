package com.dmz.airdnd.stepdefinitions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.dmz.airdnd.AbstractContainerBase;
import com.dmz.airdnd.accommodation.dto.request.AccommodationCreateRequest;
import com.dmz.airdnd.accommodation.repository.AccommodationRepository;
import com.dmz.airdnd.accommodation.service.AccommodationService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

// Scenario: 모든 필수 정보가 올바르면 숙소 생성에 성공한다
// 	Given 유효한 숙소 등록 정보가 준비되어 있다.
// 	And 저장소에 동일한 name 을 가진 숙소가 존재하지 않는다.
// 	When 호스트가 POST "/api/accommodations" 요청을 보내면
// 	Then 응답 상태로 201 Created를 받는다.
// 	And 응답 바디에 생성된 숙소의 id, 생성일시(createdAt)가 포함되어야 한다

@SpringBootTest
@AutoConfigureMockMvc
@CucumberContextConfiguration
public class CreationStepDef extends AbstractContainerBase {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AccommodationRepository accommodationRepository;

	@Autowired
	AccommodationService accommodationService;

	private AccommodationCreateRequest request;
	private ResultActions resultActions;

	@Autowired
	private ObjectMapper objectMapper;

	@Given("유효한 숙소 등록 정보가 준비되어 있다.")
	public void 유효한_숙소_등록_정보가_준비되어_있다() {
		request = new AccommodationCreateRequest(
			"accommo123", null, 50000L, "KRW", 2, 1, 1, 1, 12345L
		);
	}

	@Given("저장소에 동일한 name 을 가진 숙소가 존재하지 않는다.")
	public void 저장소에_동일한_name을_가진_숙소가_존재하지_않는다() {
		accommodationRepository.deleteAll();
	}

	@When("호스트가 숙소생성 API를 호출했을 때")
	public void 호스트가_숙소생성_API를_호출했을_때() throws Exception {
		String json = objectMapper.writeValueAsString(request);
		resultActions = mockMvc.perform(post("/api/accommodation")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json));
	}

	@Then("응답 상태로 201 Created를 받는다.")
	public void 숙소생성_응답_상태로_201_Created() throws Exception {
		resultActions.andExpect(status().isCreated());
	}

	@Then("응답 바디에 생성된 숙소의 id가 포함되어야 한다.")

}
