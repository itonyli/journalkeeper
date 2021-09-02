package io.journalkeeper.persistence.local.cache;

/**
 * @author LiYue
 * Date: 2020/3/5
 */
public interface PreloadCacheMetric {
    /**
     * Buffer 字节大小
     * @return
     */
    int getBufferSize();

    /**
     * Preload Cache 核心缓存条目数
     * @return
     */
    int getCoreCount();

    /**
     * Preload Cache 最大缓存条目数
     * @return
     */
    int getMaxCount();

    /**
     * Preload Cache 已经使用的缓存条目数
     * @return
     */
    int getUsedCount();

    /**
     * Preload Cache 已经缓存的条目数
     * @return
     */
    int getCachedCount();

}
