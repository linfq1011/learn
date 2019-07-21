package com.linfq.learn.mybatis.tkmybatis;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类.
 *
 * @author linfq
 */
@Data
public class BaseModel implements Serializable {

	/**
	 * 主键.
	 */
	@Id
	@KeySql(useGeneratedKeys = true)
	private Integer id;

	/**
	 * 修改时间.
	 */
	@Column(insertable = false, updatable = false)
	private LocalDateTime updateTime;

	/**
	 * 创建时间.
	 */
	@Column(insertable = false, updatable = false)
	private LocalDateTime createTime;

}
