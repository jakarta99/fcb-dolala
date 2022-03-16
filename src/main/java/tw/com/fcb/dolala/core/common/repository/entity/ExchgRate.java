package tw.com.fcb.dolala.core.common.repository.entity;

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
 * 匯率資料檔
 */
@Entity
@Table(name = "EXCHG_RATE")
@Data
public class ExchgRate {

    public static final String EXCHG_RATE_TYPE_BUY = "B";		//買入
    public static final String EXCHG_RATE_TYPE_SELL = "S";		//賣出
    public static final String EXCHG_RATE_TYPE_CASH_BUY = "X";	//現鈔買入
    public static final String EXCHG_RATE_TYPE_CASH_SELL = "Y";	//現鈔賣出
    public static final String EXCHG_RATE_TYPE_MONTHLY = "M";	//月結
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXCHG_RATE_SEQ_NO")
    String exchgRateSeqNo;	//序號
    
    @Column(name = "OPT_DATE")
    LocalDate optDate;	//日期
    
    @Column(name = "EXCHG_RATE_TYPE")
    String exchgRateType;	//匯率類型
    
	@Column(name = "CURRENCY")
	String currency;		//幣別
    
    @Column(name = "EXCHANGE_RATE")
    BigDecimal exchangeRate;	//匯率
    
    @Column(name = "STANDARD_CURRENCY")
    String standardCurrency;		//基準幣
    
    @Column(name = "STATUS")
    Integer status;		//資料狀態
    
}
