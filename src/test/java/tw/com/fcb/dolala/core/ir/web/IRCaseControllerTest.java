package tw.com.fcb.dolala.core.ir.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tw.com.fcb.dolala.core.FcbCoreApplication;
import tw.com.fcb.dolala.core.ir.repository.enums.ChargeType;
import tw.com.fcb.dolala.core.ir.web.cmd.SwiftMessageSaveCmd;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;


/**
 * @author Han-Ru
 * SWIFT MT103進電處理
 */
@SpringBootTest(classes = FcbCoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc

class IRCaseControllerTest {


	@Autowired
	 MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;



	@Test
	void receiveSwift() throws Exception {
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		SwiftMessageSaveCmd swiftMessageSaveCmd = new SwiftMessageSaveCmd();

		swiftMessageSaveCmd.setReferenceNo("test-swift-1");
		swiftMessageSaveCmd.setChargeType(ChargeType.valueOf("SHA"));
		swiftMessageSaveCmd.setReceiveDate(LocalDate.now());
		swiftMessageSaveCmd.setValueDate(LocalDate.now());
		swiftMessageSaveCmd.setIrAmount(BigDecimal.valueOf(100));
		swiftMessageSaveCmd.setReceiverAccount("/09357654321");
		swiftMessageSaveCmd.setSenderSwiftCode("CITIUS33XXX");
		swiftMessageSaveCmd.setCurrency("USD");

		var insert = mockMvc.perform(post("/ir/ircase/receive-swift")
						.content(mapper.writeValueAsString(swiftMessageSaveCmd))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn().getResponse().getContentAsString(Charset.defaultCharset());

		System.out.println("insert: " + insert);

	}
}
