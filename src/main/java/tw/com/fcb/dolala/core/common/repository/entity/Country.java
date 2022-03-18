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
 * 國別檔
 */
@Entity
@Table(name = "COUNTRY")
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNTRY_SEQ_NO")
    String countrySeqNo;	//流水編號
    
    @Column(name = "COUNTRY_CODE")
    String countryCode;		//國家代號兩碼
    
    @Column(name = "COUNTRY_NUMBER")
    String countryNumber;	//國家代號四碼
    
    @Column(name = "CHN_NAME")
    String chineseName;	//國家中文名稱
    
    @Column(name = "ENG_NAME")
    String englishName;	//國家英文名稱
    
    @Column(name = "CONTINENT_CODE")
    String continentCode;	//洲別代號
    
	@Column(name = "CREATE_DATE")
	LocalDate createDate;	//資料建立日期
	
	@Column(name = "AMEND_DATE")
	LocalDate amendDate;	//資料異動日期
	
	@Column(name = "FCB_AGENT_ID")
    String fcbAgentID;	//經辦代碼
	
	@Column(name = "STATUS")
    String status;	//資料狀態
}
