package com.pik;

import java.util.function.Supplier;

import com.pik.repositories.ApplicationRepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler do przekierowywania zapytań do konkretnych warstw aplikacji.
 */
@RestController
@RequestMapping(value = "/app")
public class ApplicationController {
    @Autowired
    ApplicationRepositoryUtil applicationRepositoryUtil;

    @RequestMapping("/test")
    public ResponseObject handleRequest() {
        System.out
                .println("---------------------Testing method called---------------- Mongo Template :"
                        + applicationRepositoryUtil.getMongoTemplate()
                        + " Repository Object : "
                        + applicationRepositoryUtil.getHotelRepository());
        Supplier<ResponseObject> supplier = ResponseObject::new;
        ResponseObject responseObject = supplier.get();

        responseObject.setMessage("Sample Data Object");
        return responseObject;

    }
}
