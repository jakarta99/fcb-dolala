package tw.com.fcb.dolala.core.ir.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRSwiftMessage
 * Author: Han-Ru
 * Date: 2022/3/10 下午 04:44
 * Description: IRSwiftMessage Entity
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Entity
@Table(name = "IR_SWIFT_MESSAGE")
@Data
public class IRSwiftMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "SEQ_NO")
    String seqNo;

    @Column(name = "AMOUNT")
    BigDecimal amount;

    @Column(name = "CURRENCY")
    String currency;

    @Column(name = "VALUE_DATE")
    Date valueDate;

}
