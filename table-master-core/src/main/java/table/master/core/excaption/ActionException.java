package table.master.core;

/**
 * @author zane
 * @date 2021/10/25
 */
public class ActionException extends Exception {
    private String title;
    private String context;

    public ActionException(String title, String context) {
        super(context);
        this.title = title;
        this.context = context;
    }

    public static void of(String title, String context) throws ActionException {
        throw new ActionException(title, context);
    }
}
