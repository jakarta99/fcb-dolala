package tw.com.fcb.dolala.core.common.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.fcb.dolala.core.common.repository.entity.BranchInformation;
@SpringBootTest
class BranchInformationRepositoryTest {
	
	@Autowired
	BranchInformationRepository branchInformationRepository;
	
	@Test
	void testGetByBranchId() {
		BranchInformation branchInformation = branchInformationRepository.getByBranch("101").orElseThrow();
		assertEquals("101", branchInformation.getBranch());
		
		//fail("Not yet implemented");
	}

	@Test
	void testFindById() {
		BranchInformation branchInformation = branchInformationRepository.findByBranch("091").orElseThrow();
		assertEquals("091", branchInformation.getBranch());
		//branchInformationRepository.findById("101");
		//fail("Not yet implemented");
	}

}
