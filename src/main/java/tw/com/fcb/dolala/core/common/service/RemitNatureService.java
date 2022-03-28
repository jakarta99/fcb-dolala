package tw.com.fcb.dolala.core.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.common.repository.RemitNatureRepository;
import tw.com.fcb.dolala.core.common.repository.entity.RemitNature;

@Transactional
@Service
/**
 * @author sinjen 
 * 匯款性質代碼服務
 */
public class RemitNatureService {

	@Autowired
	RemitNatureRepository remitNatureRepository;
	
	// 依remitNatureCode, remitNatureType取得remitNatureName
	public String getRemitNature(String remitNatureCode, String remitNatureType) {
		
		RemitNature remitNature = remitNatureRepository.findByRemitNatureCodeAndRemitNatureType(remitNatureCode, remitNatureType).orElse(new RemitNature());
		String remitNatureName = null;
		remitNatureName = remitNature.getRemitNatureName();
		return remitNatureName;
	}
}
