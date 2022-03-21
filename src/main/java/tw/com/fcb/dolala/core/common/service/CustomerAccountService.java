package tw.com.fcb.dolala.core.common.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.common.repository.CustomerAccountRepository;
import tw.com.fcb.dolala.core.common.repository.entity.CustomerAccountEntity;
import tw.com.fcb.dolala.core.common.web.dto.CustomerAccount;


@Transactional
@Service
public class CustomerAccountService {
	@Autowired
	CustomerAccountRepository repository;

	public CustomerAccount getCustomerAccount(String accountNumber) {
		CustomerAccount customerAccount = new CustomerAccount();
		CustomerAccountEntity customerAccountEntity = repository.findByAccountNumber(accountNumber).orElse(new CustomerAccountEntity());
		BeanUtils.copyProperties(customerAccountEntity, customerAccount);
		return customerAccount;
	}
}
