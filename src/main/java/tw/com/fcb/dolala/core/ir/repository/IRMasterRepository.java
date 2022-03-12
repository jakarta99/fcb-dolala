package tw.com.fcb.dolala.core.ir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
/**
 * @author sinjen
 * 
 */
@Repository
public interface IRMasterRepository extends JpaRepository<IRMaster,Long>{
	IRMaster findByIrNo(String irNo);
	
	//List<IRMaster> findByBeAdvBranch(String beAdvBranch);
	@Query(name="findByBeAdvBranch",nativeQuery = true,value = "select * from IR_APPLY_MASTER where BE_ADV_BRANCH=:beAdvBranch AND PRINT_ADV_MK = 'N'")
	List<IRMaster> findByBeAdvBranch(@Param("beAdvBranch") String beAdvBranch);
}
