package leapfrog_inc.appfactory.Http.Enum;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public enum CostType {
    unspecified,
    u100000,
    u300000,
    u1000000,
    o10000000;

    public String toValue() {

        switch (this) {
            case u100000:
                return "1";
            case u300000:
                return "2";
            case u1000000:
                return "3";
            case o10000000:
                return "4";
            default:
                return "0";
        }
    }
}
