package tw.com.fcb.dolala.core.ir.web.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tw.com.fcb.dolala.core.ir.repository.enums.ChargeType;

import javax.persistence.Column;
import javax.validation.constraints.*;
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
    @NotBlank(message = "發電行外匯編號(20欄位)不可為空白")
    String referenceNo;

    @FutureOrPresent(message = "Value_Date不可小於今日")
    @Schema(description = "有效日" )
    LocalDate valueDate;
    @FutureOrPresent(message = "Receive_Date不可小於今日")
    @Schema(description = "收件日做自動/單筆查詢印表時之日期" )
    LocalDate receiveDate;

    @Schema(description = "處理時間" )
    String txTime;
    @Schema(description = "匯入金額")
    @PositiveOrZero(message = "匯入金額不可負數")
    BigDecimal irAmount;

    @NotBlank(message = "幣別不可為空白")
    @Size(min = 3,max = 3)
    @Schema(description = "幣別")
    String currency;

    @Schema(description = "匯款人資訊 35x * 4")
    String senderInfo1;
    String senderInfo2;
    String senderInfo3;
    String senderInfo4;
    @Schema(description = "受款人資訊 35x * 4")
    @Size(max = 12,message = "帳號最長只能12位")
    String receiverAccount;
    String receiverInfo1;
    String receiverInfo2;
    String receiverInfo3;
    String receiverInfo4;


    @Schema(description = "手續費負擔類型",example = "SHA,BEN,OUR")
    ChargeType chargeType;

    @Schema(description = "中間行費用")
    String chargeFeeCurrency1;

    BigDecimal chargeFeeAmount1;
    String chargeFeeCurrency2;
    BigDecimal chargeFeeAmount2;
    String chargeFeeCurrency3;
    BigDecimal chargeFeeAmount3;

    @Size(max = 11, min = 11,message = "發電行swift代碼輸入有誤")
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

    @Size(max = 11, min = 11,message = "存匯行SWIFT-ID輸入錯誤")
    @Schema(description = "存匯行 SWIFT-TID" )
    String depositBank;
    @Schema(description = "同存記號" )
    String nstVstMk;

}
