package io.journalkeeper.persistence.local.cache;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.Collection;

/**
 * @author LiYue
 * Date: 2020/3/5
 */
public interface MemoryCacheManager extends Closeable {

    /**
     * 打印内存缓存指标详情
     */
    void printMetric();

    /**
     * 增加一个PreLoad实体
     * @param bufferSize buffer size
     * @param coreCount ??
     * @param maxCount ??
     */
    void addPreLoad(int bufferSize, int coreCount, int maxCount);

    /**
     * 移除一个PreLoad实体
     * @param bufferSize
     */
    void removePreLoad(int bufferSize);

    /**
     * 分配一个MMAP内存Buffer
     * @param bufferHolder
     */
    void allocateMMap(BufferHolder bufferHolder);

    /**
     * 分配一个直接内存Buffer
     * @param bufferSize
     * @param bufferHolder
     * @return
     */
    ByteBuffer allocateDirect(int bufferSize, BufferHolder bufferHolder);

    /**
     * 释放一个直接内存Buffer
     * @param byteBuffer
     * @param bufferHolder
     */
    void releaseDirect(ByteBuffer byteBuffer, BufferHolder bufferHolder);

    /**
     * 释放一个MMAP内存Buffer
     * @param bufferHolder
     */
    void releaseMMap(BufferHolder bufferHolder);

    /**
     * 获取PreLoad Cache数据
     * @return
     */
    Collection<PreloadCacheMetric> getCaches();

    /**
     * 获取最大内存大小
     * @return
     */
    long getMaxMemorySize();

    /**
     * 获取总共已经使用的内存大小
     * @return
     */
    long getTotalUsedMemorySize();

    /**
     * 获取总共已经使用的直接内存大小
     * @return
     */
    long getDirectUsedMemorySize();

    /**
     * 获取总共已经使用的MMAP内存大小
     * @return
     */
    long getMapUsedMemorySize();
}
