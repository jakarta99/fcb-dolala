package tw.com.fcb.dolala.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.ir.web.dto.IRCase;

/**
 * @author sinjen
 * S121-匯入匯款案件放行
 */
@SpringBootTest
class IRCaseAuthorizationS121ControllerTest {

	@Autowired
	IRCaseAuthorizationS121Controller S121;
	
	// S121I 查詢待放行資料
	@Test
	void testqryWaitForAuthorization() {
		String seqNo = "123456789012345";
		IRCase irCase = S121.qryWaitForAuthorization(seqNo);
		assertNotNull(irCase);
	}


	// S121A 執行MT103放行
	@Test
	void testexeCaseAuthorization() {
		String seqNo = "123456789012345";
		String exeReturnMsg = S121.exeCaseAuthorization(seqNo);
		assertNotNull(exeReturnMsg);
	}
}
