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
 * 錯誤訊息總表
 */
@Entity
@Table(name = "ERROR_MESSAGE")
@Data
public class ErrorMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ERROR_MESSAGE_SEQ_NO")
    String errorMessageSeqNo;	//流水編號
    
    @Column(name = "ERROR_CODE")
    String errorCode;	//錯誤代碼
    
    @Column(name = "ERROR_REASON")
    String errorReason;	//錯誤說明
    
	@Column(name = "CREATE_DATE")
	LocalDate createDate;	//資料建立日期
	
	@Column(name = "AMEND_DATE")
	LocalDate amendDate;	//資料異動日期
	
	@Column(name = "FCB_AGENT_ID")
    String fcbAgentID;	//經辦代碼
    
    @Column(name = "STATUS")
    String status;	//資料狀態
}
