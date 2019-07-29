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

import com.jd.journalkeeper.rpc.header.JournalKeeperHeader;
import com.jd.journalkeeper.rpc.remoting.serialize.CodecSupport;
import com.jd.journalkeeper.rpc.remoting.transport.codec.Decoder;
import com.jd.journalkeeper.rpc.remoting.transport.codec.Encoder;
import com.jd.journalkeeper.rpc.remoting.transport.command.Type;
import com.jd.journalkeeper.rpc.remoting.transport.exception.TransportException;
import com.jd.journalkeeper.rpc.server.GetServerEntriesResponse;
import com.jd.journalkeeper.rpc.server.RequestVoteResponse;
import io.netty.buffer.ByteBuf;

/**
 * @author liyue25
 * Date: 2019-04-02
 */
public class GetServerEntriesResponseCodec extends ResponseCodec<GetServerEntriesResponse> implements Type {
    @Override
    protected void encodeResponse(GetServerEntriesResponse response, ByteBuf buffer) throws Exception {
        // List<byte []> entries, long minIndex, long lastApplied
        CodecSupport.encodeList(buffer, response.getEntries(),
                (obj, buffer1) -> CodecSupport.encodeBytes(buffer1, (byte []) obj));
        CodecSupport.encodeLong(buffer, response.getMinIndex());
        CodecSupport.encodeLong(buffer, response.getLastApplied());
    }

    @Override
    protected GetServerEntriesResponse decodeResponse(JournalKeeperHeader header, ByteBuf buffer) throws Exception {
        return new GetServerEntriesResponse(
                CodecSupport.decodeList(buffer, CodecSupport::decodeBytes),
                CodecSupport.decodeLong(buffer),
                CodecSupport.decodeLong(buffer)
        );
    }

    @Override
    public int type() {
        return RpcTypes.GET_SERVER_ENTRIES_RESPONSE;
    }
}
