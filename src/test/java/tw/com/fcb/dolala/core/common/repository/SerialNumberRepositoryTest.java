package tw.com.fcb.dolala.core.common.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: SerialNumberRepositoryTest
 * Author: Han-Ru
 * Date: 2022/3/16 下午 03:07
 * Description:
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@SpringBootTest
class SerialNumberRepositoryTest {
    @Autowired
    SerialNumberRepository serialNumberRepository;

    @Test
    void findBySystemTypeAndBranch() {
        SerialNumber serialNumber = serialNumberRepository.findBySystemTypeAndBranch("IR","093").orElseThrow();
        assertEquals(0, serialNumber.getSerialNo());
    }

    @Test
    void findById(){
        SerialNumber serialNumber = serialNumberRepository.findById(1L).orElse(new SerialNumber());
        assertEquals(0, serialNumber.getSerialNo());
    }



}