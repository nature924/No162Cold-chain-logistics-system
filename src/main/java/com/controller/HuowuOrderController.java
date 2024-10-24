
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
 * 货物订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/huowuOrder")
public class HuowuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(HuowuOrderController.class);

    private static final String TABLE_NAME = "huowuOrder";

    @Autowired
    private HuowuOrderService huowuOrderService;


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
        PageUtils page = huowuOrderService.queryPage(params);

        //字典表数据转换
        List<HuowuOrderView> list =(List<HuowuOrderView>)page.getList();
        for(HuowuOrderView c:list){
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
        HuowuOrderEntity huowuOrder = huowuOrderService.selectById(id);
        if(huowuOrder !=null){
            //entity转view
            HuowuOrderView view = new HuowuOrderView();
            BeanUtils.copyProperties( huowuOrder , view );//把实体数据重构到view中
            //级联表 收货地址
            //级联表
            AddressEntity address = addressService.selectById(huowuOrder.getAddressId());
            if(address != null){
            BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setAddressId(address.getId());
            }
            //级联表 货物
            //级联表
            HuowuEntity huowu = huowuService.selectById(huowuOrder.getHuowuId());
            if(huowu != null){
            BeanUtils.copyProperties( huowu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setHuowuId(huowu.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(huowuOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody HuowuOrderEntity huowuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,huowuOrder:{}",this.getClass().getName(),huowuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            huowuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        huowuOrder.setCreateTime(new Date());
        huowuOrder.setInsertTime(new Date());
        huowuOrderService.insert(huowuOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HuowuOrderEntity huowuOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,huowuOrder:{}",this.getClass().getName(),huowuOrder.toString());
        HuowuOrderEntity oldHuowuOrderEntity = huowuOrderService.selectById(huowuOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            huowuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            huowuOrderService.updateById(huowuOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<HuowuOrderEntity> oldHuowuOrderList =huowuOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        huowuOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<HuowuOrderEntity> huowuOrderList = new ArrayList<>();//上传的东西
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
                            HuowuOrderEntity huowuOrderEntity = new HuowuOrderEntity();
//                            huowuOrderEntity.setHuowuOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            huowuOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收货地址 要改的
//                            huowuOrderEntity.setHuowuId(Integer.valueOf(data.get(0)));   //货物 要改的
//                            huowuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            huowuOrderEntity.setHuowuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            huowuOrderEntity.setInsertTime(date);//时间
//                            huowuOrderEntity.setCreateTime(date);//时间
                            huowuOrderList.add(huowuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("huowuOrderUuidNumber")){
                                    List<String> huowuOrderUuidNumber = seachFields.get("huowuOrderUuidNumber");
                                    huowuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> huowuOrderUuidNumber = new ArrayList<>();
                                    huowuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("huowuOrderUuidNumber",huowuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<HuowuOrderEntity> huowuOrderEntities_huowuOrderUuidNumber = huowuOrderService.selectList(new EntityWrapper<HuowuOrderEntity>().in("huowu_order_uuid_number", seachFields.get("huowuOrderUuidNumber")));
                        if(huowuOrderEntities_huowuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HuowuOrderEntity s:huowuOrderEntities_huowuOrderUuidNumber){
                                repeatFields.add(s.getHuowuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        huowuOrderService.insertBatch(huowuOrderList);
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
        PageUtils page = huowuOrderService.queryPage(params);

        //字典表数据转换
        List<HuowuOrderView> list =(List<HuowuOrderView>)page.getList();
        for(HuowuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HuowuOrderEntity huowuOrder = huowuOrderService.selectById(id);
            if(huowuOrder !=null){


                //entity转view
                HuowuOrderView view = new HuowuOrderView();
                BeanUtils.copyProperties( huowuOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(huowuOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    HuowuEntity huowu = huowuService.selectById(huowuOrder.getHuowuId());
                if(huowu != null){
                    BeanUtils.copyProperties( huowu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHuowuId(huowu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(huowuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
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
    public R add(@RequestBody HuowuOrderEntity huowuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,huowuOrder:{}",this.getClass().getName(),huowuOrder.toString());
            HuowuEntity huowuEntity = huowuService.selectById(huowuOrder.getHuowuId());
            if(huowuEntity == null){
                return R.error(511,"查不到该货物");
            }
            // Double huowuNewMoney = huowuEntity.getHuowuNewMoney();

            if(false){
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
//            if(yonghuEntity.getNewMoney() == null)
//                return R.error(511,"用户金额不能为空");
//            double balance = yonghuEntity.getNewMoney() - huowuEntity.getHuowuNewMoney()*huowuOrder.getBuyNumber();//余额
//            if(balance<0)
//                return R.error(511,"余额不够支付");
            huowuOrder.setHuowuOrderTypes(101); //设置订单状态为已支付
//            huowuOrder.setHuowuOrderTruePrice(0.0); //设置实付价格
            huowuOrder.setYonghuId(userId); //设置订单支付人id
            huowuOrder.setHuowuOrderUuidNumber(String.valueOf(new Date().getTime()));
            huowuOrder.setInsertTime(new Date());
            huowuOrder.setCreateTime(new Date());
                huowuOrderService.insert(huowuOrder);//新增订单


            return R.ok();
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            HuowuOrderEntity huowuOrder = huowuOrderService.selectById(id);//当前表service
            Integer huowuId = huowuOrder.getHuowuId();
            if(huowuId == null)
                return R.error(511,"查不到该货物");
            HuowuEntity huowuEntity = huowuService.selectById(huowuId);
            if(huowuEntity == null)
                return R.error(511,"查不到该货物");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            Double zhekou = 1.0;

                //计算金额
//                Double money = huowuEntity.getHuowuNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;




            huowuOrder.setHuowuOrderTypes(102);//设置订单状态为已退款
            huowuOrderService.updateAllColumnById(huowuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            huowuService.updateById(huowuEntity);//更新订单中货物的信息

            return R.ok();
    }

    /**
     * 派送
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        HuowuOrderEntity  huowuOrderEntity = huowuOrderService.selectById(id);
        huowuOrderEntity.setHuowuOrderTypes(103);//设置订单状态为已派送
        huowuOrderService.updateById( huowuOrderEntity);

        return R.ok();
    }


    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        HuowuOrderEntity  huowuOrderEntity = huowuOrderService.selectById(id);
        huowuOrderEntity.setHuowuOrderTypes(104);//设置订单状态为收货
        huowuOrderService.updateById( huowuOrderEntity);
        return R.ok();
    }

    /**
     * 发到网点
     */
    @RequestMapping("/wangdian")
    public R wangdian(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        HuowuOrderEntity  huowuOrderEntity = huowuOrderService.selectById(id);
        huowuOrderEntity.setHuowuOrderTypes(106);//设置订单状态为已派送
        huowuOrderService.updateById( huowuOrderEntity);

        return R.ok();
    }

}

