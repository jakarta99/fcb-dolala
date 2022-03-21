package tw.com.fcb.dolala.core.common.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema(description = "銀行檔")
@Data
public class BankAddressDto {

    @Schema(description = "名稱")
    String name;

    @Schema(description = "地址")
    String address;

}
