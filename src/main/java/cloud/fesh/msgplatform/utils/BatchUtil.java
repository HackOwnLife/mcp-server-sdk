package cloud.fesh.msgplatform.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class BatchUtil {

    /**
     * 按指定大小批量处理集合中的元素。
     * 注意：该方法在多线程环境下可能会导致不一致的状态或异常，建议在单线程环境中使用。
     *
     * @param collection 要处理的集合
     * @param batchFunction 批量处理函数
     * @param batchSize 每个批次的大小
     */
    public static <T> void batchDoBySizeWithCollection(Collection<T> collection, Consumer<Collection<T>> batchFunction, int batchSize) {
        // 参数校验
        if (collection == null || batchFunction == null) {
            throw new IllegalArgumentException("collection 和 batchFunction 不能为 null");
        }
        batchSize = Math.max(batchSize, 1);

        List<T> currentBatch = new ArrayList<>(batchSize); // 预先设置容量以提高性能

        if (collection.isEmpty()) {
            return;
        }

        for (T element : collection) {
            currentBatch.add(element);
            if (currentBatch.size() == batchSize) {
                try {
                    batchFunction.accept(currentBatch); // 直接传递 currentBatch
                } catch (Exception e) {
                    throw new RuntimeException("分批处理集合时发生异常: " + e.getMessage(), e);
                }
                currentBatch = new ArrayList<>(batchSize); // 创建新的批次列表，避免内存浪费
            }
        }
        // 如果最后一批不是空的，也传递给 batchFunction
        if (!currentBatch.isEmpty()) {
            try {
                batchFunction.accept(currentBatch);
            } catch (Exception e) {
                throw new RuntimeException("分批处理集合时发生异常: " + e.getMessage(), e);
            }
        }
    }
}
