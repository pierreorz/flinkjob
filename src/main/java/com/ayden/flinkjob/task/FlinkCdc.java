package com.ayden.flinkjob.task;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.flink.api.common.JobID;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayden.flinkjob.condition.ConditionalOnSystemProperty;
import com.ayden.flinkjob.entity.JobsEo;
import com.ayden.flinkjob.repository.JobsRepository;


@Component
@ConditionalOnSystemProperty(name = "job", value = "FlinkCdc")
public class FlinkCdc {
    public static Logger LOGGER = LoggerFactory.getLogger(FlinkCdc.class);

    @Autowired
	private JobsRepository jobRepository;

    @PostConstruct
    public void init() {
        List<JobsEo> result=jobRepository.findJobsUnDeploy();

        if(result!=null&&result.size()>0)
        {
            result.forEach(job->{
                try{
                    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
                    env.setParallelism(1);
                    env.enableCheckpointing(5000);
                    final StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
                    if (job.getJobName()!=null){
                        tEnv.getConfig().getConfiguration().setString("pipeline.name", job.getJobName());
                    }
                    tEnv.executeSql(job.getSource());
                    tEnv.executeSql(job.getSink());
                    JobID jobId=tEnv.executeSql(job.getConnect()).getJobClient().get().getJobID();
                    job.setJobId(jobId.toHexString());
                    job.setIsDeploy(true);
                }catch(Exception e){
                    job.setMsg(e.toString());
                }
            });
        }else{
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            env.setParallelism(1);
            env.enableCheckpointing(5000);
            final StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
            tEnv.executeSql("show databases");
        }
        jobRepository.saveAllAndFlush(result);
    }
}
