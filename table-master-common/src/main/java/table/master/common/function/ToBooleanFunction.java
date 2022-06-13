package table.master.common.function;

/**
 * @author Zane
 */
@FunctionalInterface
public interface ToBooleanFunction<T> {

    Boolean apply(T value);
}
