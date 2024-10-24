
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
 * 货物
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/huowu")
public class HuowuController {
    private static final Logger logger = LoggerFactory.getLogger(HuowuController.class);

    private static final String TABLE_NAME = "huowu";

    @Autowired
    private HuowuService huowuService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AddressService addressService;//收货地址
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private HuowuFenpeiService huowuFenpeiService;//订单分配
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
        params.put("huowuDeleteStart",1);params.put("huowuDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = huowuService.queryPage(params);

        //字典表数据转换
        List<HuowuView> list =(List<HuowuView>)page.getList();
        for(HuowuView c:list){
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
        HuowuEntity huowu = huowuService.selectById(id);
        if(huowu !=null){
            //entity转view
            HuowuView view = new HuowuView();
            BeanUtils.copyProperties( huowu , view );//把实体数据重构到view中
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
    public R save(@RequestBody HuowuEntity huowu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,huowu:{}",this.getClass().getName(),huowu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<HuowuEntity> queryWrapper = new EntityWrapper<HuowuEntity>()
            .eq("huowu_name", huowu.getHuowuName())
            .eq("huowu_types", huowu.getHuowuTypes())
            .eq("huowu_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuowuEntity huowuEntity = huowuService.selectOne(queryWrapper);
        if(huowuEntity==null){
            huowu.setHuowuDelete(1);
            huowu.setInsertTime(new Date());
            huowu.setCreateTime(new Date());
            huowuService.insert(huowu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HuowuEntity huowu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,huowu:{}",this.getClass().getName(),huowu.toString());
        HuowuEntity oldHuowuEntity = huowuService.selectById(huowu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(huowu.getHuowuPhoto()) || "null".equals(huowu.getHuowuPhoto())){
                huowu.setHuowuPhoto(null);
        }

            huowuService.updateById(huowu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<HuowuEntity> oldHuowuList =huowuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<HuowuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            HuowuEntity huowuEntity = new HuowuEntity();
            huowuEntity.setId(id);
            huowuEntity.setHuowuDelete(2);
            list.add(huowuEntity);
        }
        if(list != null && list.size() >0){
            huowuService.updateBatchById(list);
        }

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
            List<HuowuEntity> huowuList = new ArrayList<>();//上传的东西
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
                            HuowuEntity huowuEntity = new HuowuEntity();
//                            huowuEntity.setHuowuName(data.get(0));                    //货物名称 要改的
//                            huowuEntity.setHuowuUuidNumber(data.get(0));                    //货物编号 要改的
//                            huowuEntity.setHuowuPhoto("");//详情和图片
//                            huowuEntity.setHuowuTypes(Integer.valueOf(data.get(0)));   //货物类型 要改的
//                            huowuEntity.setHuowuContent("");//详情和图片
//                            huowuEntity.setHuowuDelete(1);//逻辑删除字段
//                            huowuEntity.setInsertTime(date);//时间
//                            huowuEntity.setCreateTime(date);//时间
                            huowuList.add(huowuEntity);


                            //把要查询是否重复的字段放入map中
                                //货物编号
                                if(seachFields.containsKey("huowuUuidNumber")){
                                    List<String> huowuUuidNumber = seachFields.get("huowuUuidNumber");
                                    huowuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> huowuUuidNumber = new ArrayList<>();
                                    huowuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("huowuUuidNumber",huowuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //货物编号
                        List<HuowuEntity> huowuEntities_huowuUuidNumber = huowuService.selectList(new EntityWrapper<HuowuEntity>().in("huowu_uuid_number", seachFields.get("huowuUuidNumber")).eq("huowu_delete", 1));
                        if(huowuEntities_huowuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HuowuEntity s:huowuEntities_huowuUuidNumber){
                                repeatFields.add(s.getHuowuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [货物编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        huowuService.insertBatch(huowuList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<HuowuView> returnHuowuViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = huowuOrderService.queryPage(params1);
        List<HuowuOrderView> orderViewsList =(List<HuowuOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(HuowuOrderView orderView:orderViewsList){
            Integer huowuTypes = orderView.getHuowuTypes();
            if(typeMap.containsKey(huowuTypes)){
                typeMap.put(huowuTypes,typeMap.get(huowuTypes)+1);
            }else{
                typeMap.put(huowuTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("huowuTypes",type);
            PageUtils pageUtils1 = huowuService.queryPage(params2);
            List<HuowuView> huowuViewList =(List<HuowuView>)pageUtils1.getList();
            returnHuowuViewList.addAll(huowuViewList);
            if(returnHuowuViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = huowuService.queryPage(params);
        if(returnHuowuViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnHuowuViewList.size();//要添加的数量
            List<HuowuView> huowuViewList =(List<HuowuView>)page.getList();
            for(HuowuView huowuView:huowuViewList){
                Boolean addFlag = true;
                for(HuowuView returnHuowuView:returnHuowuViewList){
                    if(returnHuowuView.getId().intValue() ==huowuView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnHuowuViewList.add(huowuView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnHuowuViewList = returnHuowuViewList.subList(0, limit);
        }

        for(HuowuView c:returnHuowuViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnHuowuViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = huowuService.queryPage(params);

        //字典表数据转换
        List<HuowuView> list =(List<HuowuView>)page.getList();
        for(HuowuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HuowuEntity huowu = huowuService.selectById(id);
            if(huowu !=null){


                //entity转view
                HuowuView view = new HuowuView();
                BeanUtils.copyProperties( huowu , view );//把实体数据重构到view中

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
    public R add(@RequestBody HuowuEntity huowu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,huowu:{}",this.getClass().getName(),huowu.toString());
        Wrapper<HuowuEntity> queryWrapper = new EntityWrapper<HuowuEntity>()
            .eq("huowu_name", huowu.getHuowuName())
            .eq("huowu_uuid_number", huowu.getHuowuUuidNumber())
            .eq("huowu_types", huowu.getHuowuTypes())
            .eq("huowu_delete", huowu.getHuowuDelete())
//            .notIn("huowu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HuowuEntity huowuEntity = huowuService.selectOne(queryWrapper);
        if(huowuEntity==null){
            huowu.setHuowuDelete(1);
            huowu.setInsertTime(new Date());
            huowu.setCreateTime(new Date());
        huowuService.insert(huowu);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

