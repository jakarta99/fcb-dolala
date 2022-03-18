package tw.com.fcb.dolala.core.common.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.common.repository.ExchgRateRepository;
import tw.com.fcb.dolala.core.common.repository.entity.ExchgRate;

/**
 * @author sinjen
 * 匯率處理服務
 */
@Transactional
@Service
public class ExchgRateService {
	@Autowired
	ExchgRateRepository repository;
	
	//依exchgRateType, currency, standardCurrency取得ExchgRate
	public BigDecimal getRate(String exchgRateType, String currency, String standardCurrency) {
		ExchgRate exchgRate = new ExchgRate();
		exchgRate = repository.findByExchgRateTypeAndCurrencyAndStandardCurrency(exchgRateType, currency, standardCurrency);
		if (exchgRate != null) {
			return exchgRate.getExchangeRate();
		} else
		{
			return null;
		}
	}
}
