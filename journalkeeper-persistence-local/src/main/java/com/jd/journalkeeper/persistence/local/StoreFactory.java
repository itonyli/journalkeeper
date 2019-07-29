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
package com.jd.journalkeeper.persistence.local;

import com.jd.journalkeeper.persistence.JournalPersistence;
import com.jd.journalkeeper.persistence.MetadataPersistence;
import com.jd.journalkeeper.persistence.PersistenceFactory;
import com.jd.journalkeeper.persistence.local.journal.PositioningStore;
import com.jd.journalkeeper.persistence.local.metadata.MetadataStore;
import com.jd.journalkeeper.utils.buffer.PreloadBufferPool;

import java.io.Closeable;
import java.io.IOException;

public class StoreFactory implements PersistenceFactory {

    @Override
    public MetadataPersistence createMetadataPersistenceInstance() {
        return new MetadataStore();
    }

    @Override
    public JournalPersistence createJournalPersistenceInstance() {
        return new PositioningStore();
    }

}
