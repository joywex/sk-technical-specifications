package com.SKTech.spring_boot_json_modifier.Service;

import com.SKTech.spring_boot_json_modifier.Dto.CurrentDto;
import com.SKTech.spring_boot_json_modifier.Entity.SkEntity;
import com.SKTech.spring_boot_json_modifier.Repository.SkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SkServiceImpl implements  SkService{

    private static final Logger logger = LoggerFactory.getLogger(SkServiceImpl.class);

    private final SkRepository skRepository;

    public SkServiceImpl(SkRepository skRepository) {
        this.skRepository = skRepository;
    }


    @Transactional
    public int modifyCurrent(Long id, int addValue) {

        try {
            Optional<SkEntity> optionalSkEntity = skRepository.findByIdWithLock(id);

            if (optionalSkEntity.isPresent()) {
                SkEntity entitySk = optionalSkEntity.get();
                CurrentDto currentDto = entitySk.getObj();

                logger.info("Текущим значением для id {} является: {}", id, currentDto.getCurrent());

                currentDto.setCurrent(currentDto.getCurrent() + addValue);
                logger.info("Новое значение в `current` теперь равно {}", currentDto.getCurrent());

                entitySk.setObj(currentDto);
                logger.info("Обновляем значение поля `obj`для сущности с id {}", id);

                skRepository.save(entitySk);
                logger.info("Сохранили полученные данные в репозитории.");

                return currentDto.getCurrent();
            } else {
                logger.error("Невозможно провести операцию. Сущность с id {} не была найдена", id);
                throw new EntityNotFoundException("Сущность не найдена с id: " + id);
            }
        } catch (Exception e) {
            System.out.println("Ошибка метода modify: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }



}
