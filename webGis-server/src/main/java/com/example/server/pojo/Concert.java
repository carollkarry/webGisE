package com.example.server.pojo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author carollkarry
 * @since 2023-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Concert对象", description="")
public class Concert implements Serializable {

    private static final long serialVersionUID = 1L;

    private String categoryname;

    private String actors;

    private String pricestr;

    private String verticalpic;

    private String cityname;

    private String showstatus;

    private String venue;

    private String showtime;

    private String cityid;

    private String id;

    private String name;


}
