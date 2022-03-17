package tw.com.fcb.dolala.core.ir.repository.entity;


import lombok.Data;
import tw.com.fcb.dolala.core.ir.repository.euum.ChargeType;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRSwiftMessageEntity
 * Author: Han-Ru
 * Date: 2022/3/10 下午 04:44
 * Description: 匯入電文檔
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Entity
@Table(name = "IR_SWIFT_MESSAGE")
@Data
public class IRSwiftMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;


    @Column(name = "SEQ_NO")
    String seqNo;

    @Column(name = "SENDER_SWIFT_CODE")
    String senderSwiftCode;

    @Column(name = "REFERENCE_NO")
    String referenceNo;

    @Column(name = "CURRENCY")
    String currency;

    @Column(name = "AMOUNT")
    BigDecimal amount;



    @Column(name = "VALUE_DATE")
    LocalDate valueDate;


    @Column(name = "SENDER_INFO1")
    String senderInfo1;
    @Column(name = "SENDER_INFO2")
    String senderInfo2;
    @Column(name = "SENDER_INFO3")
    String senderInfo3;
    @Column(name = "SENDER_INFO4")
    String senderInfo4;
    @Column(name = "RECEIVER_ACCOUNT")
    String receiverAccount;
    @Column(name = "RECEIVER_INFO1")
    String receiverInfo1;
    @Column(name = "RECEIVER_INFO2")
    String receiverInfo2;
    @Column(name = "RECEIVER_INFO3")
    String receiverInfo3;
    @Column(name = "RECEIVER_INFO4")
    String receiverInfo4;

    @Enumerated(EnumType.STRING)
    ChargeType chargeType;

    @Column(name = "CHARGE_FEE_CURRENCY1")
    String chargeFeeCurrency1;
    @Column(name = "CHARGE_FEE_AMOUNT1")
    BigDecimal chargeFeeAmount1;
    @Column(name = "CHARGE_FEE_CURRENCY2")
    String chargeFeeCurrency2;
    @Column(name = "CHARGE_FEE_AMOUNT2")
    BigDecimal chargeFeeAmount2;
    @Column(name = "CHARGE_FEE_CURRENCY3")
    String chargeFeeCurrency3;
    @Column(name = "CHARGE_FEE_AMOUNT3")
    BigDecimal chargeFeeAmount3;

}
