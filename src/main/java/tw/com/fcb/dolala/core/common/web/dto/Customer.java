package tw.com.fcb.dolala.core.common.web.dto;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: COMMON
 * Author: Hsiang
 * Date: 2022/3/21 下午 02:50
 * Description:
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Schema(description = "顧客檔")
@Data
public class Customer {
	@Schema(description = "顧客資料檔的序號")
	String customerSeqNo;
	
	@Schema(description = "客戶 ID")
	String customerId;
	
	@Schema(description = "管理分行")
	String branchID;
	
	@Schema(description = "中文姓名")
	String chineseName;
		
	@Schema(description = "英文姓名")
	String englishName;
	
	@Schema(description = "中文聯絡地址")
	String contactChineseAddress;
	
	@Schema(description = "國別")
	String country;
	
	@Schema(description = "受款人身份別")
	String beneKind;
	
	@Schema(description = "電子郵件")
	String email;
	
	@Schema(description = "客戶電話號碼")
	String custTelNo;
	
	@Schema(description = "受款人出生日期")
	LocalDate cusBirthDate;
	
	@Schema(description = "資料建立日期")
	LocalDate createDate;
	
	@Schema(description = "資料異動日期")
	LocalDate amendDate;
	
	@Schema(description = "經辦代碼")
	String fcbAgentID;
	
	@Schema(description = "資料狀態")
	String status;
	
	@Schema(description = "客戶帳戶資料")
	List<CustomerAccount> CustomerAccount;
}
