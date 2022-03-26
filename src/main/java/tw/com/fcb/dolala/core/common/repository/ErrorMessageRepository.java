package tw.com.fcb.dolala.core.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.fcb.dolala.core.common.repository.entity.Bank;
import tw.com.fcb.dolala.core.common.repository.entity.ErrorMessage;

import java.util.Optional;

@Repository
public interface ErrorMessageRepository extends JpaRepository<ErrorMessage,Long> {

    Optional<ErrorMessage> findByErrorCode(String errorCode);

}
