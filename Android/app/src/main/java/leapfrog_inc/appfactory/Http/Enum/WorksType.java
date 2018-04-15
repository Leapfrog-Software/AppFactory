package leapfrog_inc.appfactory.Http.Enum;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public enum WorksType {
    unspecified,
    o10,
    o20,
    o30,
    o40,
    o50;

    public String toValue() {

        switch (this) {
            case o10:
                return "1";
            case o20:
                return "2";
            case o30:
                return "3";
            case o40:
                return "4";
            case o50:
                return "5";
            default:
                return "0";
        }
    }
}
