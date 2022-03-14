package tw.com.fcb.dolala.core.ir.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.ir.repository.ExchgRateRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.ExchgRate;

/**
 * @author sinjen
 * 
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
