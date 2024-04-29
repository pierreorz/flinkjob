package com.ayden.flinkjob;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ayden.flinkjob.repository.JobsRepository;



@SpringBootTest
class FlinkjobApplicationTests {

	@Autowired
	private JobsRepository jobRepository;

	@Test
	public void test() throws Exception {


        Assertions.assertEquals(2, jobRepository.findJobsUnDeploy().size());

    }
}
