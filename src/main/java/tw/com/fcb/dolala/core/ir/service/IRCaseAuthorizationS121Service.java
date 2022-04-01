package tw.com.fcb.dolala.core.ir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.dolala.core.ir.http.CommonFeignClient;
import tw.com.fcb.dolala.core.ir.repository.IRCaseRepository;
import tw.com.fcb.dolala.core.ir.repository.IRMasterRepository;
import tw.com.fcb.dolala.core.ir.repository.entity.IRCaseEntity;
import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;

@Slf4j
@Transactional
@Service
public class IRCaseAuthorizationS121Service {
	
	@Autowired
    IRCaseRepository irCaseRepository;
	
	@Autowired
	IRMasterRepository irMasterRepository;
	
	@Autowired
	CommonFeignClient commonFeignClient;
	
	IRCaseEntity irCaseEntity;
	
	// s121i 查詢待放行資料
	public IRCaseEntity qryWaitForAuthorization(String seqNo)
	{
		IRCaseEntity irCaseEntity = irCaseRepository.findBySeqNo(seqNo);
		return irCaseEntity;
	}
	
	// s121a 執行MT103放行
	public IRMaster exeCaseAuthorization(String seqNo) {
		
		IRMaster irMaster = null;
		irCaseEntity = this.qryWaitForAuthorization(seqNo);		
		
		if (irCaseEntity != null)
		{
			//從電文檔搬移到主檔	
			irMaster = new IRMaster();
			irMaster.setPaidStats(0);
			irMaster.setValueDate(irCaseEntity.getValueDate());
			irMaster.setIrAmt(irCaseEntity.getIrAmount());
			irMaster.setCurency(irCaseEntity.getCurrency());
			irMaster.setBeAdvBranch("093");
			irMaster.setPrintAdvMk("Y");
			//產生外匯編號
			irMaster.setIrNo(commonFeignClient.getFxNo("S", "IRDto", "093"));
		    //新增主檔
		    irMasterRepository.save(irMaster);
		    
		    //update IRCaseEntity PROCESS_STATUS = 3 ：放行訖
		    irCaseEntity.setProcessStatus("3");
		    irCaseRepository.save(irCaseEntity);
		}

		return irMaster;
    }
}
