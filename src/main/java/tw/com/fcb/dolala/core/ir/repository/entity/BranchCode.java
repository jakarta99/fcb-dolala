package tw.com.fcb.dolala.core.ir.repository.entity;

import lombok.Data;
import tw.com.fcb.dolala.core.ir.BranchLevel;

import javax.persistence.*;

@Entity
@Table(name = "BRANCH_CODE")
@Data
public class BranchCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;  // 序號

    @Column(name = "BRANCH")
    String branch; //分行別

    @Column(name = "BRANCH_CODE")
    String branchCode; // 分行字軌

    @Enumerated(EnumType.STRING)
            @Column(name = "BRANCH_LEVEL")
    BranchLevel branchLevel; //分行種類 一類、二類、三類指定單位


}
