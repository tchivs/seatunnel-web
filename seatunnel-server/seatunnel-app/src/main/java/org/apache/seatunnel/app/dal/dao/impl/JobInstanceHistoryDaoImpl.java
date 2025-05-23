/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.app.dal.dao.impl;

import org.apache.seatunnel.app.dal.dao.IJobInstanceHistoryDao;
import org.apache.seatunnel.app.dal.entity.JobInstanceHistory;
import org.apache.seatunnel.app.dal.mapper.JobInstanceHistoryMapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import javax.annotation.Resource;

import static org.apache.seatunnel.app.utils.ServletUtils.getCurrentWorkspaceId;

@Repository
public class JobInstanceHistoryDaoImpl implements IJobInstanceHistoryDao {

    @Resource private JobInstanceHistoryMapper jobInstanceHistoryMapper;

    @Override
    public JobInstanceHistory getByInstanceId(Long jobInstanceId) {
        return jobInstanceHistoryMapper.selectOne(
                Wrappers.lambdaQuery(new JobInstanceHistory())
                        .eq(JobInstanceHistory::getId, jobInstanceId)
                        .eq(JobInstanceHistory::getWorkspaceId, getCurrentWorkspaceId()));
    }

    @Override
    public void insert(JobInstanceHistory jobInstanceHistory) {
        jobInstanceHistory.setWorkspaceId(getCurrentWorkspaceId());
        jobInstanceHistoryMapper.insert(jobInstanceHistory);
    }

    @Override
    public void updateJobInstanceHistory(JobInstanceHistory jobInstanceHistory) {
        jobInstanceHistory.setWorkspaceId(getCurrentWorkspaceId());
        jobInstanceHistoryMapper.updateById(jobInstanceHistory);
    }
}
