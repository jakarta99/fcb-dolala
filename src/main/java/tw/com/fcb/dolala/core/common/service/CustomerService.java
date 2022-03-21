package tw.com.fcb.dolala.core.common.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.common.repository.CustomerRepository;
import tw.com.fcb.dolala.core.common.repository.entity.CustomerEntity;
import tw.com.fcb.dolala.core.common.web.dto.Customer;


@Transactional
@Service
public class CustomerService {
	@Autowired
	CustomerRepository repository;
	
	public Customer getCustomer(String customerSeqNo) {
		Customer customer = new Customer();
		CustomerEntity customerEntity = repository.findByCustomerSeqNo(customerSeqNo).orElse(new CustomerEntity());
		BeanUtils.copyProperties(customerEntity, customer);
		return customer;
	}

}
