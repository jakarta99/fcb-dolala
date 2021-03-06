package tw.com.fcb.dolala.core.ir.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IRCaseRepositoryTest {

    @Autowired
    IRCaseRepository repository;

    @Test
    void testFindBySeqNo() {
    	IRCaseEntity irCaseEntity = repository.findBySeqNo("123456789012345").orElseThrow();
        assertEquals("2000.00",irCaseEntity.getIrAmount().toString());
    }
    
    @Test
    void testFindBySeqNoAndProcessStatus() {
    	IRCaseEntity irCaseEntity = repository.findBySeqNoAndProcessStatus("123456789012346", "8").orElseThrow();
        assertEquals("1500.00",irCaseEntity.getIrAmount().toString());

    }
}