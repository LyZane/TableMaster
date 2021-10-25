package table.master.common.function;

/**
 * @author Zane
 */
@FunctionalInterface
public interface CharFunction<R> {

    R apply(char value);
}

