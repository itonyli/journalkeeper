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
package com.jd.journalkeeper.rpc.remoting.example;

import com.jd.journalkeeper.rpc.remoting.transport.Transport;
import com.jd.journalkeeper.rpc.remoting.transport.TransportClient;
import com.jd.journalkeeper.rpc.remoting.transport.config.ClientConfig;
import com.jd.journalkeeper.rpc.remoting.transport.support.DefaultTransportClientFactory;

/**
 * @author liyue25
 * Date: 2019-03-28
 */
public class Client {
    public static void main(String [] args) throws Exception {
        DefaultTransportClientFactory defaultTransportClientFactory = new DefaultTransportClientFactory(new TestCodec());
        TransportClient transportClient = defaultTransportClientFactory.create(new ClientConfig());
        transportClient.start();
        Transport transport = transportClient.createTransport("localhost:9999");

        TestInterface testTpc = new TestInterfaceStub(transport);
        System.out.println("Response from server: " + testTpc.hello("JournalKeeper"));
        transport.stop();
        transportClient.stop();
    }
}
