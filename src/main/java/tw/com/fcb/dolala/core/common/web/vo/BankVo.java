package tw.com.fcb.dolala.core.common.web.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BankVo {
    String bankSeqNo;

    String swiftCode;

    String name;

    String address;

    String cityHanding;

    String country;

    String motherBankCountry;

    String isCorrespondent;

    String unsanctioned;

    LocalDate createDate;

    LocalDate amendDate;

    String fcbAgentID;

    String status;
}
