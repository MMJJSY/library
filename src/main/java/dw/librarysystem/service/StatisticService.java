package dw.librarysystem.service;

import dw.librarysystem.dto.StatisticPopularDto;
import dw.librarysystem.mapper.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    @Autowired
    StatisticMapper statisticMapper;

    public List<StatisticPopularDto> getBookByPopular(int limit) {
        return statisticMapper.getBookByPopular(limit);
    }
}
