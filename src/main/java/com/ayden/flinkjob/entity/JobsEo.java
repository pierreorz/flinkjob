package com.ayden.flinkjob.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="jobs_t")
@NoArgsConstructor
@Data
public class JobsEo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobId;
    private String source;
    private String sink;
    private String connect;
    private Boolean isDeploy;
    private Long sourceConfigId;
    private Long sinkConfigId;
    private String jobName;
    private String msg;

    @Column(insertable = true, updatable = false)
    private LocalDateTime createTime=LocalDateTime.now(ZoneId.systemDefault());
    @Column(insertable = true, updatable = true)
    private LocalDateTime updateTime=LocalDateTime.now(ZoneId.systemDefault());


    public JobsEo(String jobId,String source,String sink,String connect,Boolean isDeploy,String jobName){
        this.jobId=jobId;
        this.source=source;
        this.sink=sink;
        this.connect=connect;
        this.isDeploy=isDeploy;
        this.jobName=jobName;
    }
}