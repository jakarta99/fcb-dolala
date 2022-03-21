package tw.com.fcb.dolala.core.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.fcb.dolala.core.common.repository.entity.Bank;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {

    Optional<Bank> findBySwiftCode(String swiftCode);

}
