package tw.com.fcb.dolala.core.common.web.dto;

import java.time.LocalDate;

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
@Schema(description = "顧客帳號檔")
@Data
public class CustomerAccount {
	@Schema(description = "顧客帳號資料檔的序號")
	String customerAccountSeqNo;
	
	@Schema(description = "顧客資料檔的序號")
	String customerSeqNo;
	
	@Schema(description = "帳號")
	String accountNumber;
	
	@Schema(description = "所屬分行代碼")
	String branchID;
		
	@Schema(description = "黑名單註記")
	String blacklist;
	
	@Schema(description = "資料建立日期")
	LocalDate createDate;
	
	@Schema(description = "資料異動日期")
	LocalDate amendDate;
	
	@Schema(description = "經辦代碼")
	String fcbAgentID;
	
	@Schema(description = "資料狀態")
	String status;
	
}
