package tw.com.fcb.dolala.core.common.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import tw.com.fcb.dolala.core.common.repository.entity.ExchgRate;
import tw.com.fcb.dolala.core.common.web.dto.BankAddressDto;

@SpringBootTest
@AutoConfigureMockMvc
class CommonControllerTest {

	@Autowired
	CommonController common;

	@Autowired
	MockMvc mockMvc;

	// 讀取買賣匯匯率
	@Test
	void testGetFxRate() throws Exception {
		BigDecimal exchangeRateB = common.isGetFxRate(ExchgRate.EXCHG_RATE_TYPE_BUY, "USD", "TWD");
		BigDecimal exchangeRateS = common.isGetFxRate(ExchgRate.EXCHG_RATE_TYPE_SELL, "USD", "TWD");
		assertNotNull(exchangeRateB);
		assertNotNull(exchangeRateS);
		var exchgRateType = "B";
		var currency = "USD";
		var standardCurrency = "TWD";
		var isGetFxrate =  mockMvc.perform(get("/common/get-fxrate/{exchg-rate-type}/{currency}/{standard-currency}",exchgRateType,currency,standardCurrency))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		System.out.println("getfxrate: " + isGetFxrate);
	}

	@Test
	void testGetCustomer() throws Exception {
		var accountNumber = "09357654321";
		var isGetCustomer = mockMvc.perform(get("/common/customer-account/{accountNumber}",accountNumber))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println("getcustomer" + isGetCustomer);
	}

	// 檢核承作匯率
	@Test
	void testCheckFxRate() {
		BigDecimal exchgRate = new BigDecimal("28.34");
		boolean check = common.isCheckFxRate(exchgRate);
		assertTrue(check);
	}

	@Test
	void isGetNumberSerial() throws Exception {
		var systemType = "IR";
		var branch = "094";

		var isGetNumberSerial =  mockMvc.perform(get("/common/{systemType}/{branch}/get-number-serial",systemType,branch))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		System.out.println("getNumberSerial: " + isGetNumberSerial);
	}

	// 讀取國家代號4碼數字
	@Test
	void testGetCountryNumber() {
		String result = "1001";
		String countryCode = "TW";
		String countryNumber = null;
		countryNumber = common.isGetCountryNumber(countryCode);
		assertEquals(result, countryNumber);
	}
	
	// 讀取國家代號英文2碼
	@Test
	void testGetCountryCode() {
		String result = "TW";
		String countryCode = null;
		String countryNumber = "1001";
		countryCode = common.isGetCountryCode(countryNumber);
		assertEquals(result, countryCode);
	}
	
	// 身分證號檢核
	@Test
	void testCheckId() {
		String id1 = "A123456789";
		String id2 = "A800000014";
		boolean check1 = common.isCheckId(id1);
		boolean check2 = common.isCheckId(id2);
		assertTrue(check1);
		assertTrue(check2);
	}
	
	// 手續費計算
	@Test
	void testGetChargeFeeTWD() {
		String currency = "USD";
		BigDecimal amount = new BigDecimal("30000");
		BigDecimal chargeFee = common.isGetChargeFeeTWD(currency, amount);
		assertNotNull(chargeFee);
	}

	// 讀取匯款性質名稱
	@Test
	void testGetRemitNature() {
		String remitNatureCode = "695N";
		String remitNatureType = "2";	//(1:匯出 2:匯入)
		String remitNatureName = common.isGetRemitNature(remitNatureCode, remitNatureType);
		assertNotNull(remitNatureName);
	}


	// 顧客資料處理

	
	// 銀行資料處理
	@Test
	void getBankAdd() {
		BankAddressDto bankAddressDto = new BankAddressDto();
		bankAddressDto = common.getBankAdd("CITIUS33XXX").getData();
		assertEquals("BBAnk",bankAddressDto.getName());
	}

	
	// 分行資料處理


	// 錯誤訊息處理
	@Test
	void getErrorMessage() {
		String errorMessage;
		errorMessage = common.getErrorMessage("D001");
		assertEquals("查無資料",errorMessage);
	}
	
}
