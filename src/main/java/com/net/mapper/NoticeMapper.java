package com.net.mapper;

import com.net.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * 公告相关业务
 */
public interface NoticeMapper {
    List<Notice> selAllNotice(Map<String, Object> map);

    int addNotice(Notice notice);

    int updNotice(Notice notice);

    int delNotice(Integer id);

    Notice selNoticeId(Integer id);

    List<Notice> selNoticeByFive();
}
