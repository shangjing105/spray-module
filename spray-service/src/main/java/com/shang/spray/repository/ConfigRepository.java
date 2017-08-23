package com.shang.spray.repository;

import com.shang.spray.entity.Config;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * info:
 * Created by shang on 16/8/30.
 */
public interface ConfigRepository extends BaseRepository<Config> {

    @Query(value = "SELECT qrtz_job_details.JOB_NAME,qrtz_job_details.JOB_GROUP,qrtz_job_details.JOB_CLASS_NAME,qrtz_triggers.TRIGGER_NAME,qrtz_triggers.TRIGGER_STATE,qrtz_triggers.TRIGGER_GROUP,qrtz_cron_triggers.CRON_EXPRESSION,qrtz_cron_triggers.TIME_ZONE_ID FROM qrtz_job_details JOIN qrtz_triggers JOIN qrtz_cron_triggers ON qrtz_job_details.JOB_NAME = qrtz_triggers.JOB_NAME AND qrtz_triggers.TRIGGER_NAME = qrtz_cron_triggers.TRIGGER_NAME AND qrtz_triggers.TRIGGER_GROUP = qrtz_cron_triggers.TRIGGER_GROUP",
            nativeQuery = true)
    List<Object[]> getJobAndTriggerDetails();
}
