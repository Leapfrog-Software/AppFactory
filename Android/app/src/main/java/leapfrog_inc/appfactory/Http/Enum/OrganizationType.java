package leapfrog_inc.appfactory.Http.Enum;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public enum OrganizationType {
    unspecified,
    corporation,
    indivisual;

    public static OrganizationType create(int value) {

        switch (value) {
            case 1:
                return OrganizationType.corporation;
            case 2:
                return OrganizationType.indivisual;
            default:
                return OrganizationType.unspecified;
        }
    }

    public String toValue() {

        switch (this) {
            case corporation:
                return "1";
            case indivisual:
                return "2";
            default:
                return "0";
        }
    }

    public String toText() {

        switch (this) {
            case corporation:
                return "法人";
            case indivisual:
                return "個人";
            default:
                return "-";
        }
    }
}
