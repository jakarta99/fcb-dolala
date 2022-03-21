package tw.com.fcb.dolala.core.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.common.repository.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long>{

	Optional<CustomerEntity> findByCustomerSeqNo(String customerSeqNo);

}
