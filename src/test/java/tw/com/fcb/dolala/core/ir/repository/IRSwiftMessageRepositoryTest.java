package tw.com.fcb.dolala.core.ir.repository;

import lombok.Setter;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IRSwiftMessageRepositoryTest {

    @Autowired
    IRCaseRepository repository;

    @Test
    void findBySeqNo() {
        IRCaseEntity irSwiftMessageEntity = repository.findBySeqNo("123456789012345");
        assertEquals("2000.00",irSwiftMessageEntity.getAmount().toString());

    }
}