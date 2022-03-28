package tw.com.fcb.dolala.core.common.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.common.repository.BankRepository;
import tw.com.fcb.dolala.core.common.repository.ErrorMessageRepository;
import tw.com.fcb.dolala.core.common.repository.entity.Bank;
import tw.com.fcb.dolala.core.common.repository.entity.ErrorMessage;
import tw.com.fcb.dolala.core.common.web.vo.BankVo;

@Transactional
@Service
public class ErrorMessageService {

    @Autowired
    ErrorMessageRepository errorMessageRepository;

    public String findByErrorCode(String errorCode) {
        if (errorCode == null){
            errorCode = "    ";
        }
        String errorMessage = null;
        if (errorCode.substring(0, 4).equals("DZZZ")) {
            errorMessage = errorCode.substring(errorCode.indexOf(":")+1);
        } else {
            errorMessage =
                    errorMessageRepository.findByErrorCode(errorCode.substring(0, 4)).orElse(new ErrorMessage()).getErrorReason();
        }
        if (errorMessage == null){
            errorMessage = errorCode;
        }
        return errorMessage;
    }

}
