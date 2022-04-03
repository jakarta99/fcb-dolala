package tw.com.fcb.dolala.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;
import tw.com.fcb.dolala.core.ir.web.dto.IRDto;

/**
 * @author ijoshua29
 * S121-匯入匯款案件放行
 */
@SpringBootTest
class IRCaseAuthorizationS121ControllerTest {

	@Autowired
	IRCaseAuthorizationS121Controller S121;

	// S121I 查詢待放行資料
	@Test
	void testQryWaitForAuthorization() {
		String seqNo = "123456789012345";
		Response<IRCaseDto> response = S121.qryWaitForAuthorization(seqNo);
		assertEquals("0000", response.getCode());
	}

	// S121A 執行MT103放行
	@Test
	void testExeCaseAuthorization() {
		String seqNo = "123456789012345";
		Response<IRDto> response = S121.exeCaseAuthorization(seqNo);
		assertEquals("0000", response.getCode());
	}
}
