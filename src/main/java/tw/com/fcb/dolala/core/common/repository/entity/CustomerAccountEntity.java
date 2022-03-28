package tw.com.fcb.dolala.core.common.repository.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Hsiang
 * 顧客帳號檔
 */
@Entity
@Table(name = "CUSTOMER_ACCOUNT")
@Data
public class CustomerAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ACCOUNT_SEQ_NO")
    String customerAccountSeqNo;	//顧客帳號檔的序號
    
    @Column(name = "CUSTOMER_SEQ_NO")
    String customerSeqNo;	//顧客資料檔的序號
    
    @Column(name = "ACCOUNT_NUMBER")
    String accountNumber;	//帳號
    
    @Column(name = "BRANCH_ID")
    String branchID;	//所屬分行代碼
    
    @Column(name = "BLACKLIST")
    String blacklist;	//黑名單註記
    
	@Column(name = "CREATE_DATE")
	LocalDate createDate;	//資料建立日期
	
	@Column(name = "AMEND_DATE")
	LocalDate amendDate;	//資料異動日期
	
	@Column(name = "FCB_AGENT_ID")
    String fcbAgentID;	//經辦代碼
    
    @Column(name = "STATUS")
    String status;	//資料狀態
}
