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
package com.jd.journalkeeper.rpc.codec;

import com.jd.journalkeeper.rpc.remoting.transport.command.Type;
import com.jd.journalkeeper.rpc.server.GetServerStateRequest;
import com.jd.journalkeeper.rpc.header.JournalKeeperHeader;
import com.jd.journalkeeper.rpc.remoting.serialize.CodecSupport;
import io.netty.buffer.ByteBuf;

/**
 * @author liyue25
 * Date: 2019-03-29
 */
public class GetServerStateRequestCodec extends GenericPayloadCodec<GetServerStateRequest> implements Type {
    @Override
    protected void encodePayload(GetServerStateRequest request, ByteBuf buffer) throws Exception {
        // long lastIncludedIndex, long offset
        CodecSupport.encodeLong(buffer, request.getLastIncludedIndex());
        CodecSupport.encodeLong(buffer, request.getOffset());
    }

    @Override
    protected GetServerStateRequest decodePayload(JournalKeeperHeader header, ByteBuf buffer) throws Exception {

        return new GetServerStateRequest(
                CodecSupport.decodeLong(buffer),
                CodecSupport.decodeLong(buffer)
        );
    }

    @Override
    public int type() {
        return RpcTypes.GET_SERVER_STATE_REQUEST;
    }
}

