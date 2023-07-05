package com.nails.nastya.nailsme.web;

import com.nails.nastya.nailsme.NailsmeApplication;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(NailsmeApplication.VERSION_URL + "/statistic")
@RequiredArgsConstructor
@Tag(name = "Статистика", description = "Работа со статистикой")
public class StatisticController {
}
