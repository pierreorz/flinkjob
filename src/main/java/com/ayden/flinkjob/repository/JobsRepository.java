package com.ayden.flinkjob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ayden.flinkjob.entity.JobsEo;

public interface JobsRepository extends JpaRepository<JobsEo, Long> {

    @Query("from JobsEo u where u.jobId=:jobId")
    JobsEo findJobsById(@Param("jobId") String jobId);

    @Query("from JobsEo u where u.isDeploy is not true")
    List<JobsEo> findJobsUnDeploy();

}