package id.co.nds.shop.generators;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import id.co.nds.shop.globals.GlobalConstant;
import id.co.nds.shop.models.ResponseModel;

public class ResponseGenerator {
    private Object obj;
    private String className;
    private Object group;

    public ResponseGenerator(Object obj, String className, Object group) {
        this.obj = obj;
        this.className = className;
        this.group = group;
    }

    public ResponseEntity<ResponseModel> getResponse() {
        ResponseModel response = new ResponseModel();
        String entityName = "";
        if (obj instanceof ArrayList) {
            entityName = className.replace("Entity", "") + " List";
        } else {
            entityName = obj.getClass().getSimpleName().replace("Entity", "");
        }
        response.setMsg(String.format(GlobalConstant.RESPONSE_STRING_FORMAT.get(group), entityName));
        response.setData(obj);
        return ResponseEntity.ok(response);
    }
}
