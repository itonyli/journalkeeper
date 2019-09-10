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
package io.journalkeeper.core.entry.reserved;

import io.journalkeeper.base.Serializer;

import java.nio.ByteBuffer;

/**
 * @author LiYue
 * Date: 2019-05-09
 */
public class LeaderAnnouncementEntrySerializer implements Serializer<LeaderAnnouncementEntry> {

    @Override
    public byte[] serialize(LeaderAnnouncementEntry entry) {
        byte [] buffer = new byte[Byte.BYTES + Integer.BYTES];
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        byteBuffer.put((byte) LeaderAnnouncementEntry.TYPE_LEADER_ANNOUNCEMENT);
        byteBuffer.putInt(entry.getTerm());
        return buffer;
    }

    @Override
    public LeaderAnnouncementEntry parse(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int term =  byteBuffer.getInt(Byte.BYTES);
        return new LeaderAnnouncementEntry(term);
    }
}
