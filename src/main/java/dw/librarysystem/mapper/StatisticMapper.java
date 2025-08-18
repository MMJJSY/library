package dw.librarysystem.mapper;

import dw.librarysystem.dto.StatisticMemberDto;
import dw.librarysystem.dto.StatisticPopularDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatisticMapper {
    List<StatisticPopularDto> getBookByPopular(int limit);

    StatisticMemberDto getStatisticByMember(@Param("memberId")Long memberId);
}