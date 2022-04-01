package tw.com.fcb.dolala.core.ir.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.common.http.Response;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.service.IRCaseService;
import tw.com.fcb.dolala.core.ir.web.dto.IRCaseDto;

/**
 * @author sinjen
 * S061-作業部退匯(無匯入編號)
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class ReturnIRCaseS061Controller {

	@Autowired
	IRCaseService S061;
	@Autowired
    CommonFeignClient commonFeignClient;
	
	// ※※※ S061 API清單 ※※※
	// S061A	退匯(無匯入編號) (A)
	@PutMapping("/return-ircase/{seqNo}/execute")
	@Operation(description = "退匯(無匯入編號)", summary="退匯(無匯入編號)")
	public Response<IRCaseDto> exeReturnIRCase(@PathVariable("seqNo") String seqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = S061.exeReturnIRCase(seqNo);
			response.Success();
            response.setData(irCaseDto);
            log.info("呼叫作業部退匯API：SeqNo編號" + seqNo + "已執行退匯作業");
		}catch(Exception e){
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫作業部退匯API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	
	// S061C	退匯(無匯入編號) (C)
	// S061D	退匯(無匯入編號) (D)
	// S061P	退匯(無匯入編號) (P)
	// S061I	退匯(無匯入編號) (A/C/D/P) 前資料查詢
	@PutMapping("/return-ircase/{seqNo}/enquiry")
	@Operation(description = "查詢待退匯案件(無匯入編號)", summary="查詢待退匯案件(無匯入編號)")
	public Response<IRCaseDto> qryWaitForReturnIRCase(@PathVariable("seqNo") String seqNo) {
		Response<IRCaseDto> response = new Response<IRCaseDto>();
		try {
			IRCaseDto irCaseDto = S061.qryWaitForReturnIRCase(seqNo);
			response.Success();
            response.setData(irCaseDto);
            log.info("呼叫作業部退匯API：查詢SeqNo編號" + seqNo + "待退匯");
		}catch(Exception e){
            response.Error(e.getMessage(), commonFeignClient.getErrorMessage(e.getMessage()));
            log.info("呼叫作業部退匯API：" + commonFeignClient.getErrorMessage(e.getMessage()));
		}
		return response;
	}
	
	// TFXRR27	查詢即期結匯匯率
	// TBNMR12	查詢轉匯存匯行名稱地址。(幣別非99-台幣)
	// TBNMR13	查詢轉匯存匯行名稱地址。(幣別99-台幣)

}
