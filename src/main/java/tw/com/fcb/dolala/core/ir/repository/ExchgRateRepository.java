package tw.com.fcb.dolala.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.ir.repository.entity.ExchgRate;

/**
 * @author sinjen
 * 
 */
@Repository
public interface ExchgRateRepository extends JpaRepository<ExchgRate, Long> {

	ExchgRate findByExchgRateTypeAndCurrencyAndStandardCurrency(String exchgRateType, String currency, String standardCurrency);
}