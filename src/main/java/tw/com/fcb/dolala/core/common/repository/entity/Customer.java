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
 * 顧客主檔
 */
@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_SEQ_NO")
    String customerSeqNo;	//顧客資料檔的序號
    
    @Column(name = "BRANCH_ID")
    String branchID;	//管理分行
    
    @Column(name = "CHN_NAME")
    String chineseName;	//中文姓名
    
    @Column(name = "ENG_NAME")
    String englishName;	//英文姓名
    
    @Column(name = "CONTACT_CHN_ADDR")
    String contactChineseAddress;	//中文聯絡地址
    
    @Column(name = "COUNTRY")
    String country;	//國別
    
    @Column(name = "EMAIL")
    String email;	//電子郵件
    
	@Column(name = "CREATE_DATE")
	LocalDate createDate;	//資料建立日期
	
	@Column(name = "AMEND_DATE")
	LocalDate amendDate;	//資料異動日期
	
	@Column(name = "FCB_AGENT_ID")
    String fcbAgentID;	//經辦代碼
	
    @Column(name = "STATUS")
    String status;	//資料狀態
}
