package tw.com.fcb.dolala.core.common.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.repository.BankRepository;
import tw.com.fcb.dolala.core.common.repository.entity.Bank;
import tw.com.fcb.dolala.core.common.web.dto.BankDto;

@Transactional
@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public BankDto findBySwiftCode(String swiftCode) throws Exception {
        BankDto bankDto = new BankDto();
        Bank bank =
                bankRepository.findBySwiftCode(swiftCode).orElseThrow(() -> new Exception("找不到此swiftCode "+ swiftCode));
        BeanUtils.copyProperties(bank, bankDto);
        return bankDto;
    }

}
