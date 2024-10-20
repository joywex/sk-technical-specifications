package com.SKTech.spring_boot_json_modifier.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.SKTech.spring_boot_json_modifier.Dto.RequestDto;
import com.SKTech.spring_boot_json_modifier.Service.SkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/modify")
public class SkController {

    private static final Logger logger = LoggerFactory.getLogger(SkController.class);

    private final SkService skService;

    public SkController( SkService skService1) {
        this.skService = skService1;
    }

    @PostMapping
    public ResponseEntity<?> modify(@RequestBody RequestDto request) {
            Long id = request.getId();
            int addValue = request.getAdd();

            logger.info("Получены значения на входе по id: {} и значению: {}", id, addValue);

            int newCurrentValue = skService.modifyCurrent(id, addValue);

            logger.info("Все изменения были проведены успешно и сохранены. Текщуее значение для id {} теперь составляет: {}", id, newCurrentValue);

            return ResponseEntity.ok(Map.of("current", newCurrentValue));
    }

}
