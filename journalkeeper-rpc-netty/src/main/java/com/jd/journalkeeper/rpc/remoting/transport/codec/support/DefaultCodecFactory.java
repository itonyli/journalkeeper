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
package com.jd.journalkeeper.rpc.remoting.transport.codec.support;

import com.jd.journalkeeper.rpc.remoting.transport.codec.Codec;
import com.jd.journalkeeper.rpc.remoting.transport.codec.CodecFactory;
import com.jd.journalkeeper.rpc.remoting.transport.codec.Decoder;
import com.jd.journalkeeper.rpc.remoting.transport.codec.Encoder;

/**
 * 默认编解码器工厂
 * author: gaohaoxiang
 * email: gaohaoxiang@jd.com
 * date: 2018/8/13
 */
public class DefaultCodecFactory implements CodecFactory {

    private Decoder decoder;
    private Encoder encoder;

    public DefaultCodecFactory(Decoder decoder, Encoder encoder) {
        this.decoder = decoder;
        this.encoder = encoder;
    }

    @Override
    public Codec getCodec() {
        return new DefaultCodec(decoder, encoder);
    }
}