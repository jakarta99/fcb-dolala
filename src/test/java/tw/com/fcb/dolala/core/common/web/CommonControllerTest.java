package tw.com.fcb.dolala.core.common.web;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.repository.entity.ExchgRate;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;
import tw.com.fcb.dolala.core.common.web.dto.Customer;

@SpringBootTest
class CommonControllerTest {

	@Autowired
	CommonController common;
	
	// 讀取買賣匯匯率
	@Test
	void testGetFxRate() {
		BigDecimal exchangeRateB = common.getFxRate(ExchgRate.EXCHG_RATE_TYPE_BUY, "USD", "TWD");
		BigDecimal exchangeRateS = common.getFxRate(ExchgRate.EXCHG_RATE_TYPE_SELL, "USD", "TWD");
		assertNotNull(exchangeRateB);
		assertNotNull(exchangeRateS);
	}
	
	// 檢核承作匯率
	@Test
	void testCheckFxRate() {
		BigDecimal exchgRate = new BigDecimal("28.34");
		boolean check = common.checkFxRate(exchgRate);
		assertTrue(check);
	}
	
	// 讀取國家代號4碼數字
	@Test
	void testGetCountryNumber() {
		String result = "1001";
		String countryCode = "TW";
		String countryNumber = null;
		countryNumber = common.getCountryNumber(countryCode);
		assertEquals(result, countryNumber);
	}
	
	// 讀取國家代號英文2碼
	@Test
	void testGetCountryCode() {
		String result = "TW";
		String countryCode = null;
		String countryNumber = "1001";
		countryCode = common.getCountryCode(countryNumber);
		assertEquals(result, countryCode);
	}
	
	// 身分證號檢核
	@Test
	void testCheckId() {
		String id1 = "A123456789";
		String id2 = "A800000014";
		boolean check1 = common.checkId(id1);
		boolean check2 = common.checkId(id2);
		assertTrue(check1);
		assertTrue(check2);
	}

	// 讀取取號檔
	@Test
	void testGetNumberSerial() {
		String systemType = "IR";
		String branch = "091";
		Long serialNo = common.getNumberSerial(systemType, branch);
		assertNotNull(serialNo);
	}
	
	// 取得外匯編號FXNO
	@Test
	void testGetFxNo() {
		String noCode = "S";
		String systemType = "IR";
		String branch = "093";
		String fxNo = common.getFxNo(noCode, systemType, branch);
		assertNotNull(fxNo);
	}
	
	// 取得IRCase seqNo
	@Test
	void testGetSeqNo() {
		String irSeq = common.getSeqNo();
		assertNotNull(irSeq);
	}
	
	// 顧客資料處理
	@Test
	void testGetCustomer() {
		Customer customer = null;
		String accountNumber = "09368123456";
		customer = common.getCustomer(accountNumber);
		assertNotNull(customer);
	}
		
	// 分行資料處理
	@Test
	void testGetBranchCode() {
		String result = "NHA";
		String branch = "093";
		String branchCode = common.getBranchCode(branch);
		assertEquals(result, branchCode);
	}
	
	// 讀銀行檔
	@Test
	void testGetBank() {
		String swiftCode = "333";
		BankDto bankDto = common.getBank(swiftCode);
		assertNotNull(bankDto);
	}
	
}
