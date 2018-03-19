package todoApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoAppController {

    @RequestMapping(value = "/listTasks", method = RequestMethod.GET)
    String homePage() {
        return "homePage";
    }

}