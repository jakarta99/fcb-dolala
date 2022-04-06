package tw.com.fcb.dolala.core.ir.web.cmd;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tw.com.fcb.dolala.core.ir.repository.enums.ChargeType;

import javax.validation.constraints.Size;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: IRSaveCmd
 * Author: sinjen
 * Date: 2022/3/10 下午 02:49
 * Description:匯入匯款案件主檔
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Schema(description = "匯入匯款案件主檔")
@Data
public class IRSaveCmd {
	@Schema(description = "匯入匯款編號")
	String irNo;


	@Schema(description = "付款狀態")
	Integer paidStats;
	
	@Schema(description = "印製通知書記號")
	String printAdvMk;
	

	@Size(min = 3, max = 3, message = "再通知銀行長度僅能為3碼")
	@Schema(description = "受通知單位")
	String beAdvBranch;
	
	@Schema(description = "處理單位")
	String processBranch;

	@Size(max = 1)
	@Schema(description = "是否本行客戶(空白, Y, N)")
	String ourCust;
	
	@Schema(description = "客戶 ID")
	String customerId;
	
	@Schema(description = "幣別")
	String currency;
	
	@Schema(description = "匯入匯款金額")
	BigDecimal irAmount;
	
	@Schema(description = "外匯存款金額")
	BigDecimal fxDeposit;
	
	@Schema(description = "即期結匯金額")
	BigDecimal spotSettledFx;
	
	@Schema(description = "手續費支出")
	BigDecimal commCharge;
	
	@Schema(description = "折合美金匯率")
	BigDecimal toUsFxrate;
	
	@Schema(description = "匯款行匯出編號(匯票NO)")
	String referenceNo;
	
	@Schema(description = "匯款人一")
	String senderInfo1;
	
	@Schema(description = "匯款人二")
	String senderInfo2;
	
	@Schema(description = "匯款人三")
	String senderInfo3;
	
	@Schema(description = "匯款人四")
	String senderInfo4;
	
	@Schema(description = "受款人帳號")
	String receiverAccount;
	
	@Schema(description = "受款人一")
	String receiverInfo1;
	
	@Schema(description = "受款人二")
	String receiverInfo2;
	
	@Schema(description = "受款人三")
	String receiverInfo3;
	
	@Schema(description = "受款人四")
	String receiverInfo4;
	
	@Schema(description = "費用明細(BEN,OUR)")
	ChargeType chargeType;
	
	@Schema(description = "匯款行 SWIFT_TID")
	String remitBank;
	
	@Schema(description = "匯款行名稱一")
	String remitBkName1;
	
	@Schema(description = "匯款行名稱二")
	String remitBkName2;
	
	@Schema(description = "存匯行 SWIFT_TID")
	String depositBank;
	
	@Schema(description = "有效日")
	LocalDate valueDate;
	
	@Schema(description = "通知日")
	LocalDate advDate;
	
	@Schema(description = "印製通知書日期")
	LocalDate printAdvDate;
	
	@Schema(description = "付款日")
	LocalDate processDate;
	
	@Schema(description = "客戶電話號碼")
	String custTelNo;

	
	@Schema(description = "匯率")
	BigDecimal exchangeRate;
	
	@Schema(description = "發電者費用幣別_1")
	String chargeFeeCurrency1;
	
	@Schema(description = "發電者費用_1")
	BigDecimal chargeFeeAmount1;
	
	@Schema(description = "發電者費用幣別_2")
	String chargeFeeCurrency2;
	
	@Schema(description = "發電者費用_2")
	BigDecimal chargeFeeAmount2;
	
	@Schema(description = "發電者費用幣別_3")
	String chargeFeeCurrency3;
	
	@Schema(description = "發電者費用_3")
	BigDecimal chargeFeeAmount3;
	
	@Schema(description = "受款人身份別")
	String beneKind;
	
	@Schema(description = "匯款性質")
	String irMark;
	
	@Schema(description = "匯款性質細項分類")
	String SubCode;
	
	@Schema(description = "受款人出生日期")
	LocalDate cusBirthDate;
	
	@Schema(description = "分行注意事項一")
	String notice1;
	
	@Schema(description = "分行注意事項二")
	String notice2;
	
	@Schema(description = "分行注意事項三")
	String notice3;
}
