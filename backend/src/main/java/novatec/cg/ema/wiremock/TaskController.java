package novatec.cg.ema.wiremock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @RequestMapping("/api/fancy/task")
    public Task getSuperFancyTask() {
        return new Task(1L, "SuperFancy", "Carpe Diem");
    }
}
