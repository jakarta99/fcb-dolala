package tw.com.fcb.dolala.core.ir.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tw.com.fcb.dolala.core.ir.repository.entity.IRMaster;
/**
 * @author sinjen
 * 
 */
@Repository
public interface IRMasterRepository extends CrudRepository<IRMaster,Long>{

}
