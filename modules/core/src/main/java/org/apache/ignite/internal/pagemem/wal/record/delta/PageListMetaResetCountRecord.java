/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.pagemem.wal.record.delta;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.internal.pagemem.PageMemory;
import org.apache.ignite.internal.processors.cache.database.freelist.io.PagesListMetaIO;

/**
 * Delta record for page-list meta count reset
 */
public class PageListMetaResetCountRecord extends PageDeltaRecord  {
    /**
     * @param cacheId Cache ID.
     * @param pageId Page ID.
     */
    public PageListMetaResetCountRecord(int cacheId, long pageId) {
        super(cacheId, pageId);
    }

    /** {@inheritDoc} */
    @Override public void applyDelta(PageMemory pageMem, long pageAddr) throws IgniteCheckedException {
        PagesListMetaIO io = PagesListMetaIO.VERSIONS.forPage(pageAddr);

        io.resetCount(pageAddr);
    }

    /** {@inheritDoc} */
    @Override public RecordType type() {
        return RecordType.PAGE_LIST_META_RESET_COUNT_RECORD;
    }
}
