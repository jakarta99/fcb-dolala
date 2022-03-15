package tw.com.fcb.dolala.core.common.repository.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Copyright (C),2022-2022,FirstBank
 * FileName: SerialNumber
 * Author: Han-Ru
 * Date: 2022/3/14 下午 02:46
 * Description: 取號檔
 * Hisotry:
 * <author>     <time>       <version>     <desc>
 * 作者姓名       修改時間       版本編號       描述
 */
@Entity
@Table(name = "SERIAL_NUMBER")
@Data
public class SerialNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "SYSTEM_TYPE")
    String systemType;// 系統類別

    @Column(name = "BRANCH")
    String branch; //分行別

    @Column(name = "SERIAL_NO")
    Long serialNo; // 取號位置
}
