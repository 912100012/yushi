package com.me.excontroller;

import com.me.config.SuccessResponse;
import com.me.entity.*;
import com.me.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "业务接口")
@RestController
public class ExController {
    @Resource
    CartService cartService;
    @Resource
    FavoriteService favoriteService;
    @Resource
    OrderService orderService;
    @Resource
    ProductService productService;
    @Resource
    ShopappService shopappService;
    @Resource
    TypeService typeService;
    @Resource
    UserService userService;

    @ApiOperation(value = "单独购买接口", notes = "传一个uid和pid即可，后端自动判断是数量加一还是新增记录，余额自动扣除")
    @PostMapping("/buy")
    public SuccessResponse<?> buy(@RequestBody Order order) {
        Order order1 = orderService.queryOne(order);
        Product product = productService.queryById(order.getPid());
        Double price = product.getPrice();
        User user = userService.queryById(order.getUid());
        if (user.getBalance() < price) {
            return new SuccessResponse<Order>(null, "余额不足");
        }
        if (order1 == null) {
            order.setCount(1);
            order.setCtime(new Date());
            order1 = orderService.insert(order);
        } else {
            order1.setCount(order1.getCount() + 1);
            order1.setCtime(new Date());
            orderService.update(order1);
        }
        user.setBalance(user.getBalance() - price);
        userService.update(user);
        return new SuccessResponse<Order>(order1, "购买成功");
    }

    @ApiOperation(value = "个人空间", notes = "只传一个当前登录的“用户id”，就获取到对应的个人信息,我的收藏，我的购物车，我的订单")
    @PostMapping("/center")
    public SuccessResponse<?> center(@RequestBody User user) {
        User oneuser = userService.queryOne(user);
        Favorite favorite = new Favorite();
        favorite.setUid(oneuser.getId());
        // 获取我的收藏
        List<Favorite> favorites = favoriteService.queryListByLimit(favorite);
        List<Product> f_products = null;
        if (favorites != null) {
            f_products = favorites.stream().map(one_favorite -> productService.queryById(one_favorite.getPid())).collect(Collectors.toList());
        }
        //我的购物车
        Cart cart = new Cart();
        cart.setUid(oneuser.getId());
        List<Cart> carts = cartService.queryListByLimit(cart);
        List<Product> c_products = null;
        if (carts != null) {
            c_products = carts.stream().map(one_cart -> productService.queryById(one_cart.getPid())).collect(Collectors.toList());
        }
        //我的订单
        Order order = new Order();
        order.setUid(oneuser.getId());
        List<Order> orders = orderService.queryListByLimit(order);
        List<Product> o_products = null;
        if (orders != null) {
            o_products = orders.stream().map(one_order -> productService.queryById(one_order.getPid())).collect(Collectors.toList());
        }
        // 创建一个 Map 来存储不同类型的商品集合
        Map<String, Object> data = new HashMap<>();
        data.put("me", oneuser);
        data.put("favs", f_products);
        data.put("carts", c_products);
        data.put("orders", o_products);
        return new SuccessResponse<Map>(data, "请求成功");
    }


    @ApiOperation(value = "种类字典", notes = "可以放到前端全局里，取的时候方便，key是种类id，value是种类名字")
    @GetMapping("/typedic")
    public SuccessResponse<?> center() {
        Map<Integer, String> data = new HashMap<>();
        List<Type> types = typeService.queryList();
        types.stream().forEach(type -> data.put(type.getId(), type.getType()));
        return new SuccessResponse<Map>(data, "种类字典请求成功");
    }

    @ApiOperation(value = "操作购物车接口", notes = "body传入uid，pid，参数携带opt,p代表增加，m代表减少，字符串类型")
    @PostMapping("/optCart")
    public SuccessResponse<?> optCart(@RequestParam("opt") String opt, @RequestBody Cart cart) {
        Cart onecart = cartService.queryOne(cart);
        if ("p".equals(opt)) {
            if (onecart == null) {
                onecart = new Cart();
                onecart.setCount(1);
                onecart.setUid(cart.getUid());
                onecart.setPid(cart.getPid());
                onecart.setCtime(new Date());
                onecart = cartService.insert(onecart);
            } else {
                onecart.setCount(onecart.getCount() + 1);
                onecart.setCtime(new Date());
                cartService.update(onecart);
            }
            return new SuccessResponse<>(onecart, "商品已成功添加到购物车");
        } else if ("m".equals(opt)) {
            // 执行减少操作
            if (onecart == null) {
                return new SuccessResponse<>(false, "没有购物车，删除失败", 500);
            } else {
                if (onecart.getCount() == 1) {
                    cartService.deleteById(onecart.getId());
                    return new SuccessResponse<>(null, "删除了最后一件产品，已清空");
                } else {
                    onecart.setCount(onecart.getCount() - 1);
                    cartService.update(onecart);
                    return new SuccessResponse<>(onecart, "删除一件产品");
                }
            }

        } else {
            return new SuccessResponse<>(false, "无效的操作动作,只接受参数为'+'或'-'");
        }
    }

    //收藏
    @ApiOperation(value = "收藏", notes = "传入uid，pid,自动判断是否收藏过了")
    @PostMapping("/fav")
    public SuccessResponse<?> fav(@RequestBody Favorite fav) {
        Favorite favorite = favoriteService.queryOne(fav);
        if (favorite != null) {
            return new SuccessResponse<>(false, "已经收藏过了");
        } else {
            favoriteService.insert(fav);
            return new SuccessResponse<>(true, "收藏成功");
        }
    }

    //批量购买
    @ApiOperation(value = "批量购买（清空购物车）", notes = "传入cart对象集合")
    @PostMapping("/batch/buy")
    public SuccessResponse<?> batch_buy(@RequestBody List<Cart> cartList) {
        double total = 0.0;
        User user = userService.queryById(cartList.get(0).getUid());
        for (Cart cart : cartList) {
            Integer count = cart.getCount();
            Product product = productService.queryById(cart.getPid());
            double one_total = product.getPrice()*count;
            total += one_total;
        }
        if (total > user.getBalance()){
            return new SuccessResponse<>(false, "余额不足。");
        }else{
            for (Cart cart : cartList) {
                Integer count = cart.getCount();
                Product product = productService.queryById(cart.getPid());
                for (int i = 0; i < count; i++) {
                    Order order = new Order();
                    order.setUid(user.getId());
                    order.setPid(product.getId());
                    buy(order);
                }
                cartService.deleteById(cart.getId());
            }
            return new SuccessResponse<>(true, "购买成功。");
        }
    }


}
