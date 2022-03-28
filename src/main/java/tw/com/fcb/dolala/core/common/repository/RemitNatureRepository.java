package tw.com.fcb.dolala.core.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.common.repository.entity.RemitNature;

/**
 * @author sinjen
 * 匯款性質代碼檔
 */
@Repository
public interface RemitNatureRepository extends JpaRepository<RemitNature, Long> {

	Optional<RemitNature> findByRemitNatureCodeAndRemitNatureType(String remitNatureCode, String remitNatureType);
	
}
