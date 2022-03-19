package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.ir.repository.IRCaseRepository;
import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
import tw.com.fcb.dolala.core.ir.web.dto.IR;
import tw.com.fcb.dolala.core.ir.web.dto.IRCase;

@Slf4j
@Transactional
@Service
public class IRCaseAuthorizationS121Service {
	
	@Autowired
    IRCaseRepository irCaseRepository;
	
	@Autowired
	IRMasterRepository irMasterRepository;
	
	IRCase irCase = new IRCase();
	
	public IRCase qryWaitForAuthorization(String seqNo)
	{
		IRCaseEntity irCaseEntity = irCaseRepository.findBySeqNo(seqNo);
		BeanUtils.copyProperties(irCaseEntity, irCase);
		return irCase;
	}
	
	public String exeCaseAuthorization(String seqNo) {
		
		String returnMsg = "";
		irCase = this.qryWaitForAuthorization(seqNo);		
		
		if (irCase != null)
		{
			//從電文檔搬移到主檔
			IR ir = new IR();
			ir.setValueDate(irCase.getValueDate());
			ir.setIrAmt(irCase.getAmount());
			ir.setCurency(irCase.getCurrency());
			
			//新增主檔
			IRMaster irMaster = new IRMaster();
		    BeanUtils.copyProperties(ir, irMaster);
		    irMasterRepository.save(irMaster);
		    returnMsg = "新增主檔成功";
		}
		else
		{
			returnMsg = "查無資料";
		}
		return returnMsg;
    }
}
