package tw.com.fcb.dolala.core.ir.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tw.com.fcb.dolala.core.ir.repository.euum.ChargeType;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRSwiftMessageEntity
 * Author: Han-Ru
 * Date: 2022/3/15 下午 02:24
 * Description: 電文檔dto
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Schema(description = "匯入匯款電文檔")
@Data
public class IRSwiftMessage {
    @Schema(description = "swift序號")
    String seqNo;
    @Schema(description = "發電行swift代號")
    String senderSwiftCode;
    @Schema(description = "發電行外匯編號(20欄位)")
    String referenceNo;
    @Schema(description = "生效日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate valueDate;
    @Schema(description = "金額")
    BigDecimal amount;
    @Schema(description = "幣別")
    String currency;
    @Schema(description = "發電行資訊 35x * 4")
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

}
