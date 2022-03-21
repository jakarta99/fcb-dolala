package tw.com.fcb.dolala.core.ir.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.ir.service.IRCaseAuthorizationS121Service;
import tw.com.fcb.dolala.core.ir.web.dto.IRCase;

/**
 * @author sinjen
 * S121-匯入匯款案件放行
 */
@Slf4j
@RestController
@RequestMapping("/ir")
public class IRCaseAuthorizationS121Controller {

	@Autowired
	IRCaseAuthorizationS121Service irCaseAuthorizationS121Service;
	
	
	IRCaseEntity irCaseEntity;
			
	// ※※※ S121 API清單 ※※※
	// S121I 查詢待放行資料
	@PutMapping("/s121i")
    @Operation(description = "查詢待放行資料", summary="查詢待放行")
    public String qryWaitForAuthorization(String seqNo) {
		String qryReturnMsg = "";
		irCaseEntity = irCaseAuthorizationS121Service.qryWaitForAuthorization(seqNo);		
		
		if (irCaseEntity != null)
		{
			qryReturnMsg = "查詢待放行資料 = " + irCaseEntity.toString();
			log.info(qryReturnMsg);			 
		}
		else
		{
			qryReturnMsg = "查無資料";
			log.info(qryReturnMsg);
		}
		
		return qryReturnMsg;
    }	
	
	// S121A 執行MT103放行
	@PutMapping("/s121a")
    @Operation(description = "執行MT103放行", summary="MT103放行")
    public String exeCaseAuthorization(String seqNo) {
		String exeReturnMsg = irCaseAuthorizationS121Service.exeCaseAuthorization(seqNo);
		log.info(exeReturnMsg);
		return exeReturnMsg;
    }
}
