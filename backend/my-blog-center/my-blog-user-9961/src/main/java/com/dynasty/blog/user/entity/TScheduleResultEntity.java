package com.dynasty.blog.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author dynasty
 * @email study_wc@163.com
 * @date 2024-06-01 13:06:04
 */
@Data
@TableName("t_schedule_result")
public class TScheduleResultEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 车间名称
     */
    private String factorName;
    
    /**
     * 工单编号
     */
    private String orderNum;
    
    /**
     * 机器数量
     */
    private String machineNum;
    
    /**
     * 工件数量
     */
    private Integer workpiecesNum;
    
    /**
     * 调度员
     */
    private String staffName;
    
    /**
     * 调度结果图
     */
    private String dataUrl;
    /**
     * 关联数据集
     */
    private String dataSet;
    
    
    /**
     * 调度状态
     */
    private String state;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
	
	/**
	 * 逻辑删除状态
	 */
	@TableLogic()
	@TableField(value = "deleted", fill = FieldFill.INSERT_UPDATE)
	private Integer deleted;
	/**
	 * 乐观锁
	 */
	@TableField(value = "version", fill = FieldFill.INSERT)
	@Version
	private Integer version;
    
}
