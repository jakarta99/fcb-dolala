package tw.com.fcb.dolala.core.common.repository.entity;

import lombok.Data;
import tw.com.fcb.dolala.core.common.BranchType;

import javax.persistence.*;

@Entity
@Table(name = "BRANCH_INFORMATION")
@Data
public class BranchInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "BRANCH")
    String branch; //分行別

    @Column(name = "BRANCH_CODE")
    String branchCode; // 分行字軌

    @Enumerated(EnumType.STRING)
            @Column(name = "BRANCH_TYPE")
    BranchType branchType; //分行種類 一類、二類、三類指定單位

    @Column(name = "ASSIGN_ZONE")
    String assignZone; //所屬區域中心

    @Column(name = "BRANCH_NAME")
    String branchName;//分行中文名稱

    @Column(name = "BRANCH_ADDRESS")
    String branchAddress; //分行地址

    @Column(name = "BRANCH_PHONE")
    String branchPhone; //分行電話


}
