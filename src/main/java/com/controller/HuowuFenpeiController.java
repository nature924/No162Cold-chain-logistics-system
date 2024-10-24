
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
 * 订单分配
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/huowuFenpei")
public class HuowuFenpeiController {
    private static final Logger logger = LoggerFactory.getLogger(HuowuFenpeiController.class);

    private static final String TABLE_NAME = "huowuFenpei";

    @Autowired
    private HuowuFenpeiService huowuFenpeiService;


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
    private HuowuOrderService huowuOrderService;//货物订单
    @Autowired
    private KuaidiyuanService kuaidiyuanService;//快递员
    @Autowired
    private LiuyanService liuyanService;//留言板
    @Autowired
    private WangdianService wangdianService;//网点信息
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
        PageUtils page = huowuFenpeiService.queryPage(params);

        //字典表数据转换
        List<HuowuFenpeiView> list =(List<HuowuFenpeiView>)page.getList();
        for(HuowuFenpeiView c:list){
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
        HuowuFenpeiEntity huowuFenpei = huowuFenpeiService.selectById(id);
        if(huowuFenpei !=null){
            //entity转view
            HuowuFenpeiView view = new HuowuFenpeiView();
            BeanUtils.copyProperties( huowuFenpei , view );//把实体数据重构到view中
            //级联表 货物订单
            //级联表
            HuowuOrderEntity huowuOrder = huowuOrderService.selectById(huowuFenpei.getHuowuOrderId());
            if(huowuOrder != null){
            BeanUtils.copyProperties( huowuOrder , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "kuaidiyuanId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setHuowuOrderId(huowuOrder.getId());
            }
            //级联表 快递员
            //级联表
            KuaidiyuanEntity kuaidiyuan = kuaidiyuanService.selectById(huowuFenpei.getKuaidiyuanId());
            if(kuaidiyuan != null){
            BeanUtils.copyProperties( kuaidiyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "kuaidiyuanId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setKuaidiyuanId(kuaidiyuan.getId());
            }
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
    public R save(@RequestBody HuowuFenpeiEntity huowuFenpei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,huowuFenpei:{}",this.getClass().getName(),huowuFenpei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("快递员".equals(role))
            huowuFenpei.setKuaidiyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<HuowuFenpeiEntity> queryWrapper = new EntityWrapper<HuowuFenpeiEntity>()
            .eq("huowu_order_id", huowuFenpei.getHuowuOrderId())
            .eq("kuaidiyuan_id", huowuFenpei.getKuaidiyuanId())
            .eq("huowu_order_fenpei_types", huowuFenpei.getHuowuOrderFenpeiTypes())
            .eq("fenpei_wendu", huowuFenpei.getFenpeiWendu())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuowuFenpeiEntity huowuFenpeiEntity = huowuFenpeiService.selectOne(queryWrapper);
        if(huowuFenpeiEntity==null){
            huowuFenpei.setCreateTime(new Date());
            huowuFenpeiService.insert(huowuFenpei);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HuowuFenpeiEntity huowuFenpei, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,huowuFenpei:{}",this.getClass().getName(),huowuFenpei.toString());
        HuowuFenpeiEntity oldHuowuFenpeiEntity = huowuFenpeiService.selectById(huowuFenpei.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
        oldHuowuFenpeiEntity.setHuowuOrderFenpeiTypes(2);
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("快递员".equals(role))
//            huowuFenpei.setKuaidiyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            huowuFenpeiService.updateById(huowuFenpei);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<HuowuFenpeiEntity> oldHuowuFenpeiList =huowuFenpeiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        huowuFenpeiService.deleteBatchIds(Arrays.asList(ids));

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
            List<HuowuFenpeiEntity> huowuFenpeiList = new ArrayList<>();//上传的东西
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
                            HuowuFenpeiEntity huowuFenpeiEntity = new HuowuFenpeiEntity();
//                            huowuFenpeiEntity.setHuowuOrderId(Integer.valueOf(data.get(0)));   //订单 要改的
//                            huowuFenpeiEntity.setKuaidiyuanId(Integer.valueOf(data.get(0)));   //快递员 要改的
//                            huowuFenpeiEntity.setHuowuOrderFenpeiTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            huowuFenpeiEntity.setFenpeiWendu(Integer.valueOf(data.get(0)));   //物流温度 要改的
//                            huowuFenpeiEntity.setFenpeiTime(sdf.parse(data.get(0)));          //分配时间 要改的
//                            huowuFenpeiEntity.setCreateTime(date);//时间
                            huowuFenpeiList.add(huowuFenpeiEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        huowuFenpeiService.insertBatch(huowuFenpeiList);
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
        PageUtils page = huowuFenpeiService.queryPage(params);

        //字典表数据转换
        List<HuowuFenpeiView> list =(List<HuowuFenpeiView>)page.getList();
        for(HuowuFenpeiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HuowuFenpeiEntity huowuFenpei = huowuFenpeiService.selectById(id);
            if(huowuFenpei !=null){


                //entity转view
                HuowuFenpeiView view = new HuowuFenpeiView();
                BeanUtils.copyProperties( huowuFenpei , view );//把实体数据重构到view中

                //级联表
                    HuowuOrderEntity huowuOrder = huowuOrderService.selectById(huowuFenpei.getHuowuOrderId());
                if(huowuOrder != null){
                    BeanUtils.copyProperties( huowuOrder , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHuowuOrderId(huowuOrder.getId());
                }
                //级联表
                    KuaidiyuanEntity kuaidiyuan = kuaidiyuanService.selectById(huowuFenpei.getKuaidiyuanId());
                if(kuaidiyuan != null){
                    BeanUtils.copyProperties( kuaidiyuan , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setKuaidiyuanId(kuaidiyuan.getId());
                }
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
    public R add(@RequestBody HuowuFenpeiEntity huowuFenpei, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,huowuFenpei:{}",this.getClass().getName(),huowuFenpei.toString());
        Wrapper<HuowuFenpeiEntity> queryWrapper = new EntityWrapper<HuowuFenpeiEntity>()
            .eq("huowu_order_id", huowuFenpei.getHuowuOrderId())
            .eq("kuaidiyuan_id", huowuFenpei.getKuaidiyuanId())
            .eq("huowu_order_fenpei_types", huowuFenpei.getHuowuOrderFenpeiTypes())
            .eq("fenpei_wendu", huowuFenpei.getFenpeiWendu())
//            .notIn("huowu_fenpei_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuowuFenpeiEntity huowuFenpeiEntity = huowuFenpeiService.selectOne(queryWrapper);
        if(huowuFenpeiEntity==null){
            huowuFenpei.setCreateTime(new Date());
        huowuFenpeiService.insert(huowuFenpei);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

