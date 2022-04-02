package id.co.nds.shop.globals;

import java.util.HashMap;
import java.util.Map;

import id.co.nds.shop.controllers.ControllerGroup.DeletingById;
import id.co.nds.shop.controllers.ControllerGroup.GettingAll;
import id.co.nds.shop.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.shop.controllers.ControllerGroup.GettingById;
import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.UpdatingById;

public class GlobalConstant {
    public static final String REC_STATUS_ACTIVE = "A";
    public static final String REC_STATUS_NON_ACTIVE = "N";

    public static final Map<Object, String> RESPONSE_STRING_FORMAT = new HashMap<>() {
        {
            put(PostingNew.class, "New %s is successfully added");
            put(GettingAll.class, "%s are successfully obtained");
            put(GettingAllByCriteria.class, "%s are successfully obtained");
            put(GettingById.class, "%s is successfully obtained");
            put(UpdatingById.class, "%s is successfully updated");
            put(DeletingById.class, "%s is successfully deleted");
        }
    };

}
