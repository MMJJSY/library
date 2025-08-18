package dw.librarysystem.mapper;

import dw.librarysystem.dto.StatisticPopularDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticMapper {
    List<StatisticPopularDto> getBookByPopular(int limit);

}
