
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 网点信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/wangdian")
public class WangdianController {
    private static final Logger logger = LoggerFactory.getLogger(WangdianController.class);

    private static final String TABLE_NAME = "wangdian";

    @Autowired
    private WangdianService wangdianService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private HuowuService huowuService;//货物
    @Autowired
    private HuowuFenpeiService huowuFenpeiService;//订单分配
    @Autowired
    private HuowuOrderService huowuOrderService;//货物订单
    @Autowired
    private KuaidiyuanService kuaidiyuanService;//快递员
    @Autowired
    private LiuyanService liuyanService;//留言板
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("快递员".equals(role))
            params.put("kuaidiyuanId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = wangdianService.queryPage(params);

        //字典表数据转换
        List<WangdianView> list =(List<WangdianView>)page.getList();
        for(WangdianView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WangdianEntity wangdian = wangdianService.selectById(id);
        if(wangdian !=null){
            //entity转view
            WangdianView view = new WangdianView();
            BeanUtils.copyProperties( wangdian , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody WangdianEntity wangdian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wangdian:{}",this.getClass().getName(),wangdian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<WangdianEntity> queryWrapper = new EntityWrapper<WangdianEntity>()
            .eq("wangdian_name", wangdian.getWangdianName())
            .eq("lianxiren_name", wangdian.getLianxirenName())
            .eq("lianxiren_phone", wangdian.getLianxirenPhone())
            .eq("lianxiren_address", wangdian.getLianxirenAddress())
            .eq("wangdian_types", wangdian.getWangdianTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WangdianEntity wangdianEntity = wangdianService.selectOne(queryWrapper);
        if(wangdianEntity==null){
            wangdian.setCreateTime(new Date());
            wangdianService.insert(wangdian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WangdianEntity wangdian, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,wangdian:{}",this.getClass().getName(),wangdian.toString());
        WangdianEntity oldWangdianEntity = wangdianService.selectById(wangdian.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(wangdian.getWangdainPhoto()) || "null".equals(wangdian.getWangdainPhoto())){
                wangdian.setWangdainPhoto(null);
        }

            wangdianService.updateById(wangdian);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WangdianEntity> oldWangdianList =wangdianService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        wangdianService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<WangdianEntity> wangdianList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            WangdianEntity wangdianEntity = new WangdianEntity();
//                            wangdianEntity.setWangdianName(data.get(0));                    //网点名称 要改的
//                            wangdianEntity.setWangdainPhoto("");//详情和图片
//                            wangdianEntity.setLianxirenName(data.get(0));                    //联系人姓名 要改的
//                            wangdianEntity.setLianxirenPhone(data.get(0));                    //联系人手机号 要改的
//                            wangdianEntity.setLianxirenAddress(data.get(0));                    //地址 要改的
//                            wangdianEntity.setWangdianTypes(Integer.valueOf(data.get(0)));   //网点类型 要改的
//                            wangdianEntity.setWangdianContent("");//详情和图片
//                            wangdianEntity.setCreateTime(date);//时间
                            wangdianList.add(wangdianEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        wangdianService.insertBatch(wangdianList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = wangdianService.queryPage(params);

        //字典表数据转换
        List<WangdianView> list =(List<WangdianView>)page.getList();
        for(WangdianView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WangdianEntity wangdian = wangdianService.selectById(id);
            if(wangdian !=null){


                //entity转view
                WangdianView view = new WangdianView();
                BeanUtils.copyProperties( wangdian , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody WangdianEntity wangdian, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,wangdian:{}",this.getClass().getName(),wangdian.toString());
        Wrapper<WangdianEntity> queryWrapper = new EntityWrapper<WangdianEntity>()
            .eq("wangdian_name", wangdian.getWangdianName())
            .eq("lianxiren_name", wangdian.getLianxirenName())
            .eq("lianxiren_phone", wangdian.getLianxirenPhone())
            .eq("lianxiren_address", wangdian.getLianxirenAddress())
            .eq("wangdian_types", wangdian.getWangdianTypes())
//            .notIn("wangdian_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WangdianEntity wangdianEntity = wangdianService.selectOne(queryWrapper);
        if(wangdianEntity==null){
            wangdian.setCreateTime(new Date());
        wangdianService.insert(wangdian);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

