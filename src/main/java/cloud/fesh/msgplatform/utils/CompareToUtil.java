package cloud.fesh.msgplatform.utils;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class CompareToUtil<T> {

    private static final ThreadLocal<Comparer> threadLocal = new InheritableThreadLocal<>();

    static class Comparer<T> {

        //左边的一个集合
        private Collection<T> left;
        //右边的一个集合
        private Collection<T> right;
        //左差集
        private Collection<T> leftExcept;
        //右差集
        private Collection<T> rightExcept;
        //交集
        private Collection<T> intersection;
        //并集
        private Collection<T> union;

        public Comparer(Collection<T> left, Collection<T> right) {
            this.left = left;
            this.right = right;
        }

        public Collection<T> getLeft() {
            return left;
        }

        public void setLeft(Collection<T> left) {
            this.left = left;
        }

        public Collection<T> getRight() {
            return right;
        }

        public void setRight(Collection<T> right) {
            this.right = right;
        }

        public Collection<T> getLeftExcept() {
            if (leftExcept == null) {
                if (left == null || left.isEmpty()) {
                    this.leftExcept = Collections.emptyList();
                } else if (right == null || right.isEmpty()) {
                    this.leftExcept = left;
                } else {
                    this.leftExcept = this.left.stream().filter(l -> !right.contains(l)).collect(Collectors.toList());
                }
            }
            return leftExcept;
        }

        public void setLeftExcept(Collection<T> leftExcept) {
            this.leftExcept = leftExcept;
        }

        public Collection<T> getRightExcept() {
            if (this.rightExcept == null) {
                if (right == null || right.isEmpty()) {
                    this.rightExcept = Collections.emptyList();
                } else if (left == null || left.isEmpty()) {
                    this.rightExcept = right;
                } else {
                    this.rightExcept = this.right.stream().filter(r -> !left.contains(r)).collect(Collectors.toList());
                }
            }
            return rightExcept;
        }

        public void setRightExcept(Collection<T> rightExcept) {
            this.rightExcept = rightExcept;
        }

        public Collection<T> getIntersection() {
            if (intersection == null) {
                if ((left == null || left.isEmpty()) && (right == null || right.isEmpty())) {
                    intersection = Collections.emptyList();
                } else {
                    intersection = this.left.stream().filter(l -> right.contains(l)).collect(Collectors.toList());
                }
            }
            return intersection;
        }

        public void setIntersection(Collection<T> intersection) {
            this.intersection = intersection;
        }

        public Collection<T> getUnion() {
            if (this.union == null) {
                union = new ArrayList<>(getLeftExcept().size() + getIntersection().size() + getRightExcept().size());
                union.addAll(getLeftExcept());
                union.addAll(getIntersection());
                union.addAll(getRightExcept());
            }
            return union;
        }

        public void setUnion(Collection<T> union) {
            this.union = union;
        }
    }

    public static <T> Map<CompareToEnum, Collection<T>> compare(Collection<T> left, Collection<T> right) {
        // 参数校验
        if (left == null || right == null) {
            throw new IllegalArgumentException("集合不能为null");
        }

        // 使用泛型来确保类型安全
        Comparer<T> comparer = new Comparer<>(left, right);
        threadLocal.set(comparer);

        // 使用EnumMap替代HashMap以提高效率和明确性
        EnumMap<CompareToEnum, Collection<T>> map = new EnumMap<>(CompareToEnum.class);
        map.put(CompareToEnum.UNION, comparer.getUnion());
        map.put(CompareToEnum.LEFT, comparer.getLeft());
        map.put(CompareToEnum.RIGHT, comparer.getRight());
        map.put(CompareToEnum.LEFT_EXCEPT, comparer.getLeftExcept());
        map.put(CompareToEnum.RIGHT_EXCEPT, comparer.getRightExcept());
        map.put(CompareToEnum.INTERSECTION, comparer.getIntersection());

        // 清理ThreadLocal
        threadLocal.remove();

        return map;
    }
}
