package leapfrog_inc.appfactory.Http.Enum;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public enum GallerySortType {
    evaluate,
    cost;

    public String toValue() {

        switch (this) {
            case evaluate:
                return "0";
            case cost:
                return "2";
            default:
                return "";
        }
    }
}
