package tw.com.fcb.dolala.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.fcb.dolala.core.ir.repository.entity.BranchCode;
import tw.com.fcb.dolala.core.ir.repository.entity.ExchgRate;

@Repository
public interface BranchCodeRepository extends JpaRepository<BranchCode,Long> {
    BranchCode findByBranchCode(String branch);

}
