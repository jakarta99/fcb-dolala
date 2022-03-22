package tw.com.fcb.dolala.core.common.web.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "銀行檔")
@Data
public class BankVo {
    @Schema(description = "流水編號")
    String bankSeqNo;

    @Schema(description = "SWIFTCODE")
    String swiftCode;

    @Schema(description = "名稱")
    String name;

    @Schema(description = "地址")
    String address;

    @Schema(description = "所在城市")
    String cityHanding;

    @Schema(description = "所在(登記)國家")
    String country;

    @Schema(description = "母公司國別")
    String motherBankCountry;

    @Schema(description = "是否為通匯行")
    String isCorrespondent;

    @Schema(description = "是否屬於制裁銀行(0:非制裁、1: 制裁)")
    String unsanctioned;

    @Schema(description = "資料建立日期")
    LocalDate createDate;

    @Schema(description = "資料異動日期")
    LocalDate amendDate;

    @Schema(description = "經辦代碼")
    String fcbAgentID;

    @Schema(description = "資料狀態")
    String status;
}
