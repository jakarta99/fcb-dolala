package tw.com.fcb.dolala.core.ir.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.ir.repository.enums.ChargeType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Han-Ru
 * SWIFT MT103進電處理
 */
@SpringBootTest
@AutoConfigureMockMvc
class IRCaseControllerTest {

	@Autowired
	IRCaseController IRCase;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void receiveSwift() throws Exception {

		String result = mockMvc.perform(post("/ir/swift?seqNo=string&referenceNo=string&valueDate=2022-03-28&irAmount=0&currency=TWD&senderInfo1=string&senderInfo2=string&senderInfo3=string&senderInfo4=string&receiverAccount=%2F09340469905&receiverInfo1=string&receiverInfo2=string&receiverInfo3=string&receiverInfo4=string&chargeType=SHA&chargeFeeCurrency1=string&chargeFeeAmount1=0&chargeFeeCurrency2=string&chargeFeeAmount2=0&chargeFeeCurrency3=string&chargeFeeAmount3=0&senderSwiftCode=string&remitBankInfo1=string&remitBankInfo2=string&remitBankInfo3=string&remitBankInfo4=string&depositBank=string"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();


	}
}
