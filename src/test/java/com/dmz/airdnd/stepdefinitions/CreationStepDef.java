package com.dmz.airdnd.stepdefinitions;

import static org.hamcrest.Matchers.*;
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
		request = AccommodationCreateRequest.builder()
			.name("accommodation123")
			.description(null)
			.pricePerDay(50000L)
			.currency("KRW")
			.maxGuests(2)
			.bedCount(1)
			.bedroomCount(1)
			.bathroomCount(1)
			.country("대한민국")
			.baseAddress("서울특별시 강남구 강남대로62길 23")
			.detailedAddress("")
			.build();
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

	@Then("응답 본문은 success는 true이고 바디에 생성된 숙소의 정보가 포함되어야 한다.")
	public void 응답_본문은_success는_true이고_바디에_생성된_숙소의_정보가_포함되어야_한다() throws Exception {
		resultActions
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.data.id").isNumber())
			.andExpect(jsonPath("$.data.name").value(request.getName()))
			.andExpect(jsonPath("$.data.pricePerDay").value(request.getPricePerDay()))
			.andExpect(jsonPath("$.data.currency").value(request.getCurrency()))
			.andExpect(jsonPath("$.data.maxGuests").value(request.getMaxGuests()))
			.andExpect(jsonPath("$.data.createdAt").exists())
			.andExpect(jsonPath("$.error").value(nullValue()));
	}
}
