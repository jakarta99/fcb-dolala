package tw.com.fcb.dolala.core.common.repository.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author sinjen
 * 銀行檔
 */
@Entity
@Table(name = "BANK")
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BANK_SEQ_NO")
    String bankSeqNo;	//流水編號
    
	@Column(name = "SWIFTCODE")
    String swiftCode;	//SWIFTCODE
	
	@Column(name = "NAME")
    String name;	//名稱
	
	@Column(name = "ADDRESS")
    String address;	//地址
	
	@Column(name = "CITY_HEADING")
    String cityHanding;	//所在城市
	
	@Column(name = "COUNTRY")
    String country;	//所在(登記)國家
	
	@Column(name = "MAJOR_BANK_COUNTRY")
    String motherBankCountry;	//母公司國別
	
	@Column(name = "IS_CORRESPONDENT")
    String isCorrespondent;	//是否為通匯行
	
	@Column(name = "UNSANCTIONED")
    String unsanctioned;	//是否屬於制裁銀行(0:非制裁、1: 制裁)
	
	@Column(name = "CREATE_DATE")
	LocalDate createDate;	//資料建立日期
	
	@Column(name = "AMEND_DATE")
	LocalDate amendDate;	//資料異動日期
	
	@Column(name = "FCB_AGENT_ID")
    String fcbAgentID;	//經辦代碼
	
	@Column(name = "STATUS")
    String status;	//資料狀態
}
