package tw.com.fcb.dolala.core.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.common.repository.entity.CustomerAccountEntity;

@Repository
public interface CustomerAccountRepository  extends JpaRepository<CustomerAccountEntity, Long> {
	
	// 以帳號讀取顧客資料
	Optional<CustomerAccountEntity> findByAccountNumber(String accountNumber);
}
