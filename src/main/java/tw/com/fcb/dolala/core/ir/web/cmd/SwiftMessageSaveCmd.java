package tw.com.fcb.dolala.core.ir.web.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tw.com.fcb.dolala.core.ir.repository.enums.ChargeType;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: SwiftMessageSaveCmd
 * Author: Han-Ru
 * Date: 2022/3/10 下午 02:36
 * Description:
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Schema(description = "匯入SWIFT資料")
@Data
public class SwiftMessageSaveCmd {
    @Schema(description = "swift序號")
    String seqNo;

    @Schema(description = "發電行外匯編號(20欄位)")
    String referenceNo;
    @Schema(description = "生效日")
    LocalDate valueDate;
    @Schema(description = "匯入金額")
    BigDecimal irAmount;
    @Schema(description = "幣別")
    String currency;
    @Schema(description = "匯款人資訊 35x * 4")
    String senderInfo1;
    String senderInfo2;
    String senderInfo3;
    String senderInfo4;
    @Schema(description = "受款人資訊 35x * 4")
    String receiverAccount;
    String receiverInfo1;
    String receiverInfo2;
    String receiverInfo3;
    String receiverInfo4;


    @Schema(description = "手續負擔類型",example = "SHA,BEN,OUR")
    ChargeType chargeType;
    @Schema(description = "中間行費用")
    String chargeFeeCurrency1;
    BigDecimal chargeFeeAmount1;
    String chargeFeeCurrency2;
    BigDecimal chargeFeeAmount2;
    String chargeFeeCurrency3;
    BigDecimal chargeFeeAmount3;

    @Schema(description = "發電行 swift 代號" )
    String senderSwiftCode;

    @Schema(description = "匯款行一" )
    String remitBankInfo1;

    @Schema(description = "匯款行二" )
    String remitBankInfo2;

    @Schema(description = "匯款行三" )
    String remitBankInfo3;

    @Schema(description = "匯款行四" )
    String remitBankInfo4;

    @Schema(description = "存匯行 SWIFT-TID" )
    String depositBank;
}
