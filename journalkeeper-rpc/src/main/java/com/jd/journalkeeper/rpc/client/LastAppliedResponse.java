/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jd.journalkeeper.rpc.client;

import com.jd.journalkeeper.rpc.LeaderResponse;

/**
 * RPC 方法
 * {@link com.jd.journalkeeper.rpc.client.ClientServerRpc#lastApplied() lastApplied()}
 * 返回响应。
 * @author liyue25
 * Date: 2019-03-14
 */
public class LastAppliedResponse  extends LeaderResponse {
    private final long lastApplied;
    public LastAppliedResponse(Throwable throwable){
        this(throwable, -1L);
    }

    public LastAppliedResponse(long lastApplied) {
        this(null, lastApplied);
    }

    private LastAppliedResponse(Throwable exception, long lastApplied) {
        super(exception);
        this.lastApplied = lastApplied;
    }

    /**
     * 集群当前状态对应的Journal索引序号。
     * @return 集群当前状态对应的Journal索引序号。
     */
    public long getLastApplied() {
        return lastApplied;
    }
}
