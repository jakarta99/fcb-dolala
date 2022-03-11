package tw.com.fcb.dolala.core.ir.repository.entity;

import java.math.BigDecimal;
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
 * 匯入匯款案件主檔
 */
@Entity
@Table(name = "IR_APPLY_MASTER")
@Data
public class IRMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CASE_SEQ_NO")
    String caseSeqNo;	//匯入匯款主檔系統編號
    
    @Column(name = "IR_NO")
    String irNo;	//匯入匯款編號
    
    @Column(name = "PAID_STATS")
    Integer paidStats;	//付款狀態
    
    @Column(name = "PRINT_ADV_MK")
    String printAdvMk;	//印製通知書記號
    
    @Column(name = "BE_ADV_BRANCH")
    String beAdvBranch;	//受通知單位
    
    @Column(name = "PROCESS_BRANCH")
    String processBranch;	//處理單位
    
    @Column(name = "OUR_CUST")
    String ourCust;	//是否本行客戶(空白, Y, N)
    
    @Column(name = "CUSTOMER_ID")
    String customerId;	//客戶 ID
    
    @Column(name = "CURENCY")
    String curency;	//幣別
    
    @Column(name = "IR_AMT")
    BigDecimal irAmt;	//匯入匯款金額
    
    @Column(name = "FX_DEPOSIT")
    BigDecimal fxDeposit;	//外匯存款金額
    
    @Column(name = "SPOT_SETTLED_FX")
    BigDecimal spotSettledFx;	//即期結匯金額
    
    @Column(name = "COMM_CHARGE")
    BigDecimal commCharge;	//手續費支出
    
    @Column(name = "TO_US_FXRATE")
    BigDecimal toUsFxrate;	//折合美金匯率
    
    @Column(name = "SW20")
    String sw20;	//匯款行匯出編號(匯票NO)
    
    @Column(name = "SW50_1")
    String sw501;	//匯款人一
    
    @Column(name = "SW50_2")
    String sw502;	//匯款人二
    
    @Column(name = "SW50_3")
    String sw503;	//匯款人三
    
    @Column(name = "SW50_4")
    String sw504;	//匯款人四
    
    @Column(name = "SW59_AC")
    String sw59Ac;	//受款人帳號
    
    @Column(name = "SW59_1")
    String sw591;	//受款人一
    
    @Column(name = "SW59_2")
    String sw592;	//受款人二
    
    @Column(name = "SW59_3")
    String sw593;	//受款人三
    
    @Column(name = "SW59_4")
    String sw594;	//受款人四
    
    @Column(name = "SW71A")
    String sw71A;	//費用明細(BEN,OUR)
    
    @Column(name = "REMIT_BANK")
    String remitBank;	//匯款行 SWIFT_TID
    
    @Column(name = "REMIT_BK_NAME1")
    String remitBkName1;	//匯款行名稱一
    
    @Column(name = "REMIT_BK_NAME2")
    String remitBkName2;	//匯款行名稱二
    
    @Column(name = "DEPOSIT_BANK")
    String depositBank;	//存匯行 SWIFT_TID
    
    @Column(name = "VALUE_DATE")
    LocalDate valueDate;	//有效日
    
    @Column(name = "ADV_DATE")
    LocalDate advDate;	//通知日
    
    @Column(name = "PRINT_ADV_DATE")
    LocalDate printAdvDate;	//印製通知書日期
    
    @Column(name = "PROCESS_DATE")
    LocalDate processDate;	//付款日
    
    @Column(name = "CUST_TEL_NO")
    String custTelNo;	//客戶電話號碼
    
    @Column(name = "AC_NO")
    String acNo;	//SW59_AC受款人帳號
    
    @Column(name = "EXCHANGE_RATE")
    BigDecimal exchangeRate;	//匯率
    
    @Column(name = "SW71F_CUR_1")
    String sw71fCur1;	//發電者費用幣別_1
    
    @Column(name = "SW71F_1")
    BigDecimal sw71f1;	//發電者費用_1
    
    @Column(name = "SW71F_CUR_2")
    String sw71fCur2;	//發電者費用幣別_2
    
    @Column(name = "SW71F_2")
    BigDecimal sw71f2;	//發電者費用_2
    
    @Column(name = "SW71F_CUR_3")
    String sw71fCur3;	//發電者費用幣別_3
    
    @Column(name = "SW71F_3")
    BigDecimal sw71f3;	//發電者費用_3
    
    @Column(name = "BENE_KIND")
    String beneKind;	//受款人身份別
    
    @Column(name = "IR_MARK")
    String irMark;	//匯款性質
    
    @Column(name = "SUB_CODE")
    String SubCode;	//匯款性質細項分類
    
    @Column(name = "CUS_BIRTH_DATE")
    LocalDate cusBirthDate;	//受款人出生日期
    
    @Column(name = "NOTICE_1")
    String notice1;	//分行注意事項一
    
    @Column(name = "NOTICE_2")
    String notice2;	//分行注意事項二
    
    @Column(name = "NOTICE_3")
    String notice3;	//分行注意事項三
}