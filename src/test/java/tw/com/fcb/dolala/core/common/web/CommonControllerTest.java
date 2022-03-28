package tw.com.fcb.dolala.core.common.web;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.repository.entity.ExchgRate;

@SpringBootTest
class CommonControllerTest {

	@Autowired
	CommonController common;
	
	// 讀取買賣匯匯率
	@Test
	void testGetFxRate() {
		BigDecimal exchangeRateB = common.isGetFxRate(ExchgRate.EXCHG_RATE_TYPE_BUY, "USD", "TWD");
		BigDecimal exchangeRateS = common.isGetFxRate(ExchgRate.EXCHG_RATE_TYPE_SELL, "USD", "TWD");
		assertNotNull(exchangeRateB);
		assertNotNull(exchangeRateS);
	}
	
	// 檢核承作匯率
	@Test
	void testCheckFxRate() {
		BigDecimal exchgRate = new BigDecimal("28.34");
		boolean check = common.isCheckFxRate(exchgRate);
		assertTrue(check);
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

	// 顧客資料處理

	
	// 銀行資料處理

	
	// 分行資料處理
	
	
}
