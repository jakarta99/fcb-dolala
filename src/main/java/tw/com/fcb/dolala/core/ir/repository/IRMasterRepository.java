package tw.com.fcb.dolala.core.ir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
/**
 * @author sinjen
 * 
 */
@Repository
public interface IRMasterRepository extends JpaRepository<IRMaster,Long>{
	IRMaster findByIrNo(String irNo);
	List<IRMaster> findByBeAdvBranch(String beAdvBranch);

}
