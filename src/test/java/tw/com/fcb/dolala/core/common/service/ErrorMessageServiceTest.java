package tw.com.fcb.dolala.core.common.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ErrorMessageServiceTest {

    @Autowired
    ErrorMessageService errorMessageService;

    @Test
    void findByErrorCode() {
        assertEquals("餘額小於0",errorMessageService.findByErrorCode("DZZZ:餘額小於0"));
        assertEquals("查無資料",errorMessageService.findByErrorCode("D001,999"));
        assertEquals("查無資料",errorMessageService.findByErrorCode("D001"));
        assertEquals("ABCD",errorMessageService.findByErrorCode("ABCD"));
    }
}