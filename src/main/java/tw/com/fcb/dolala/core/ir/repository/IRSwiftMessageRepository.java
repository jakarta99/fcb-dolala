package tw.com.fcb.dolala.core.ir.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.dolala.core.ir.repository.entity.IRSwiftMessage;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRSwiftMessageRepository
 * Author: Han-Ru
 * Date: 2022/3/10 下午 03:34
 * Description: IRSwiftMessageRepository
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Repository
public interface IRSwiftMessageRepository extends CrudRepository<IRSwiftMessage,Long> {

}
