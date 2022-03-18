package tw.com.fcb.dolala.core.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.common.repository.entity.Country;

/**
 * @author sinjen
 * 國別檔
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
	
	//以國家代號英文2碼讀取國別檔
	Country findByCountryCode(String countryCode);
	
	//以國家代號4碼數字讀取國別檔
	Country findByCountryNumber(String countryNumber);

}
