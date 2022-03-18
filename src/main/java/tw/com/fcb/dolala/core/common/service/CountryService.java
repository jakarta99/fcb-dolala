package tw.com.fcb.dolala.core.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.fcb.dolala.core.common.repository.CountryRepository;
import tw.com.fcb.dolala.core.common.repository.entity.Country;

/**
 * @author sinjen
 * 國別處理服務
 */
@Transactional
@Service
public class CountryService {
	@Autowired
	CountryRepository repository;
	
	//以國家代號英文2碼讀取國家代號4碼數字
	public String getCountryNumber(String countryCode) {
		Country country = new Country();
		country = repository.findByCountryCode(countryCode);
		if(country != null) {
			return country.getCountryNumber();
		} else {
			return null;
		}
	}
	
	//以國家代號4碼數字讀取國家代號英文2碼
	public String getCountryCode(String countryNumber) {
		Country country = new Country();
		country = repository.findByCountryNumber(countryNumber);
		if(country != null) {
			return country.getCountryCode();
		} else {
			return null;
		}
	}
}
