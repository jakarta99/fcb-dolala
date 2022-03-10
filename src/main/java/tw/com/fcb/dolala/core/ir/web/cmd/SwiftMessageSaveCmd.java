package tw.com.fcb.dolala.core.ir.web.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tw.com.fcb.dolala.core.ir.ChargeType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

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
    @Schema(description = "發電行swift代號")
    String senderSwiftCode;
    @Schema(description = "發電行外匯編號(20欄位)")
    String referenceNo;
    @Schema
    LocalDate valueDate;

    BigDecimal amount;
    String currency;

    String senderInfo1;
    String senderInfo2;
    String senderInfo3;
    String senderInfo4;

    String receiverAccount;
    String receiverInfo1;
    String receiverInfo2;
    String receiverInfo3;
    String receiverInfo4;
    @Schema(description = "手續負擔類型",example = "SHA,BEN,OUR")
    ChargeType chargeType;

    String chargeFeeCurrency1;
    String chargeFeeAmount1;
    String chargeFeeCurrency2;
    String chargeFeeAmount2;
    String chargeFeeCurrency3;
    String chargeFeeAmount3;

}
