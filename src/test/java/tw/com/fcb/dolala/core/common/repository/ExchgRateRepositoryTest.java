package tw.com.fcb.dolala.core.common.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.repository.entity.ExchgRate;
import tw.com.fcb.dolala.core.common.service.ExchgRateService;

@SpringBootTest
class ExchgRateRepositoryTest {

	@Autowired
    ExchgRateService rateService;
	
	@Test
	void testgetExchgRate() {
		rateService.getRate(ExchgRate.EXCHG_RATE_TYPE_BUY, "USD", "TWD");
	}
	
	@Test
	void testgetExchgRate2() {
		BigDecimal sample = new BigDecimal("28.34");
		BigDecimal exchangeRate = rateService.getRate(ExchgRate.EXCHG_RATE_TYPE_BUY, "USD", "TWD");
		assertEquals(sample ,exchangeRate);
	}

}
