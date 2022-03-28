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
 * 匯款性質代碼檔
 */
@Entity
@Table(name = "REMIT_NATURE")
@Data
public class RemitNature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REMIT_NATURE_SEQ_NO")
    String remitNatureSeqNo;	//流水編號
    
    @Column(name = "REMIT_NATURE_CODE")
    String remitNatureCode;	//申報性質代碼
    
    @Column(name = "REMIT_NATURE_NAME")
    String remitNatureName;	//申報性質名稱
    
    @Column(name = "REMIT_NATURE_TYPE")
    String remitNatureType;	//匯款性質分類(1:匯出 2:匯入)
    
	@Column(name = "CREATE_DATE")
	LocalDate createDate;	//資料建立日期
	
	@Column(name = "AMEND_DATE")
	LocalDate amendDate;	//資料異動日期
	
	@Column(name = "FCB_AGENT_ID")
    String fcbAgentID;	//經辦代碼
	
	@Column(name = "STATUS")
    String status;	//資料狀態
}
