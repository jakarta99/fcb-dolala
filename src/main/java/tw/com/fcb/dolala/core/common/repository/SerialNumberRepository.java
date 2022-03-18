package tw.com.fcb.dolala.core.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.fcb.dolala.core.common.repository.entity.SerialNumber;

import java.util.Optional;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: SerialNumberRepository
 * Author: Han-Ru
 * Date: 2022/3/14 下午 02:46
 * Description: 取號檔
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Repository
public interface SerialNumberRepository extends JpaRepository<SerialNumber,Long> {
    Optional<SerialNumber> findBySystemTypeAndBranch(String systemType, String branch);

}
