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
package com.jd.journalkeeper.coordinating.client;

import com.jd.journalkeeper.base.Serializer;
import com.jd.journalkeeper.coordinating.state.domain.StateReadRequest;
import com.jd.journalkeeper.coordinating.state.domain.StateResponse;
import com.jd.journalkeeper.coordinating.state.domain.StateWriteRequest;
import com.jd.journalkeeper.coordinating.state.serializer.KryoSerializer;
import com.jd.journalkeeper.core.client.Client;
import com.jd.journalkeeper.rpc.RpcAccessPointFactory;
import com.jd.journalkeeper.rpc.client.ClientServerRpcAccessPoint;
import com.jd.journalkeeper.utils.spi.ServiceSupport;

import java.net.URI;
import java.util.List;
import java.util.Properties;

/**
 * CoordinatingClientAccessPoint
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2019/6/10
 */
public class CoordinatingClientAccessPoint {

    private Properties config;
    private Serializer<StateWriteRequest> entrySerializer;
    private Serializer<StateReadRequest> querySerializer;
    private Serializer<StateResponse> resultSerializer;

    public CoordinatingClientAccessPoint(Properties config) {
        this(config,
                new KryoSerializer<>(StateWriteRequest.class),
                new KryoSerializer<>(StateReadRequest.class),
                new KryoSerializer<>(StateResponse.class));
    }

    public CoordinatingClientAccessPoint(Properties config,
                                         Serializer<StateWriteRequest> entrySerializer,
                                         Serializer<StateReadRequest> querySerializer,
                                         Serializer<StateResponse> resultSerializer) {
        this.config = config;
        this.entrySerializer = entrySerializer;
        this.querySerializer = querySerializer;
        this.resultSerializer = resultSerializer;
    }

    public CoordinatingClient createClient(List<URI> servers) {
        RpcAccessPointFactory rpcAccessPoint = ServiceSupport.load(RpcAccessPointFactory.class);
        ClientServerRpcAccessPoint clientServerRpcAccessPoint = rpcAccessPoint.createClientServerRpcAccessPoint(servers, config);
        Client<StateWriteRequest, StateReadRequest, StateResponse> client = new Client<>(clientServerRpcAccessPoint, entrySerializer, querySerializer, resultSerializer, config);
        return new CoordinatingClient(servers, config, client);
    }
}