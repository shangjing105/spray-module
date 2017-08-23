package com.shang.spray.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shang.spray.entity.JobAndTrigger;
import com.shang.spray.repository.ConfigRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * info:
 * Created by shang on 2017/8/18.
 */
@Service
public class JobAndTriggerService{

    @Resource
    protected ConfigRepository configRepository;

    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        List<JobAndTrigger> triggerList = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Object[]> list = configRepository.getJobAndTriggerDetails();
        for (Object object : list) {
            Object[] map = (Object[]) object;

            JobAndTrigger jobAndTrigger = new JobAndTrigger();
            jobAndTrigger.setJOB_NAME(map[0].toString());
            jobAndTrigger.setJOB_GROUP(map[1].toString());
            jobAndTrigger.setJOB_CLASS_NAME(map[2].toString());
            jobAndTrigger.setTRIGGER_NAME(map[3].toString());
            jobAndTrigger.setTRIGGER_STATE(map[4].toString());
            jobAndTrigger.setTRIGGER_GROUP(map[5].toString());
            jobAndTrigger.setCRON_EXPRESSION(map[6].toString());
            jobAndTrigger.setTIME_ZONE_ID(map[7].toString());
            triggerList.add(jobAndTrigger);
        }
        return new PageInfo<>(triggerList);
    }
}
