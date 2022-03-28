package tw.com.fcb.dolala.core.common.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.common.repository.entity.ExchgRate;

/**
 * @author sinjen 
 * 手續費計算服務
 */
@Transactional
@Service
public class ChargeFeeCalculateService {

	@Autowired
	ExchgRateService fxService;
	
	// 依currency, amount取得chargeFee(新台幣)
	// 1.按本行牌告小額賣出匯率計收。
	// 2.按解款總金額乘 0.5/1,000 計收，最低NTD200，最高 NTD800，計收一次手續費用。
	public BigDecimal chargeFeeTWDCalculat(String currency, BigDecimal amount) {
		BigDecimal exchangeRate = fxService.getRate(ExchgRate.EXCHG_RATE_TYPE_SELL, currency, "TWD");
		BigDecimal feeRate = new BigDecimal("0.0005");
		BigDecimal fee200 = new BigDecimal("200");
		BigDecimal fee800 = new BigDecimal("800");
		BigDecimal chargeFee = amount.multiply(exchangeRate).multiply(feeRate);
		// System.out.println("Original chargeFee：" + chargeFee);

		if (chargeFee.compareTo(fee200) < 1) {
			chargeFee = fee200;
		} else if (chargeFee.compareTo(fee800) > -1) {
			chargeFee = fee800;
		} else {
			chargeFee = chargeFee.setScale(0, BigDecimal.ROUND_HALF_UP);
		}
		// System.out.println("New chargeFee：" + chargeFee);
		return chargeFee;
	}
}
