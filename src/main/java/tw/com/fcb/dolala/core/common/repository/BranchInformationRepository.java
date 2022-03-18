package tw.com.fcb.dolala.core.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.fcb.dolala.core.common.repository.entity.BranchInformation;

import java.util.Optional;

@Repository
public interface BranchInformationRepository extends JpaRepository<BranchInformation,Long> {
        Optional<BranchInformation> findByBranch(String branch);
        Optional<BranchInformation> getByBranch(String branch);

}
