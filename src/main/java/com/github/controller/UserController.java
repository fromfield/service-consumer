/*
 * ............................................. 
 * 
 * 				    _ooOoo_ 
 * 		  	       o8888888o 
 * 	  	  	       88" . "88 
 *                 (| -_- |) 
 *                  O\ = /O 
 *              ____/`---*\____ 
 *               . * \\| |// `. 
 *             / \\||| : |||// \ 
 *           / _||||| -:- |||||- \ 
 *             | | \\\ - /// | | 
 *            | \_| **\---/** | | 
 *           \  .-\__ `-` ___/-. / 
 *            ___`. .* /--.--\ `. . __ 
 *        ."" *< `.___\_<|>_/___.* >*"". 
 *      | | : `- \`.;`\ _ /`;.`/ - ` : | | 
 *         \ \ `-. \_ __\ /__ _/ .-` / / 
 *======`-.____`-.___\_____/___.-`____.-*====== 
 * 
 * ............................................. 
 *              佛祖保佑 永无BUG 
 *
 * 佛曰: 
 * 写字楼里写字间，写字间里程序员； 
 * 程序人员写程序，又拿程序换酒钱。 
 * 酒醒只在网上坐，酒醉还来网下眠； 
 * 酒醉酒醒日复日，网上网下年复年。 
 * 但愿老死电脑间，不愿鞠躬老板前； 
 * 奔驰宝马贵者趣，公交自行程序员。 
 * 别人笑我忒疯癫，我笑自己命太贱； 
 * 不见满街漂亮妹，哪个归得程序员？
 *
 * 北纬30.√  154518484@qq.com
 */
package com.github.controller;

import com.github.service.UserService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
@RequestMapping("user")
public class UserController {

	@Resource private UserService userService;
	@Resource private RestTemplate restTemplate;
	@Resource private LoadBalancerClient loadBalancerClient;

	@RequestMapping({"", "/", "index"})
	public String index() {
		return "service-consumer";
	}

	@ResponseBody
	@GetMapping("{id}")
	public Object userInfo(@PathVariable Integer id) {

		// 这种方式要去掉@LoadBalanced才行, 不然请求被拦截了, 而instances找不到

		ServiceInstance instance = loadBalancerClient.choose("SERVICE-PRODUCER");
		System.err.println(loadBalancerClient);
		System.err.println(instance);
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/{id}";
		return restTemplate.getForObject(url, Object.class, id);
	}

	@ResponseBody
	@GetMapping("{id}/info")
	public Object userInfo1(@PathVariable Integer id) {

		String url = "http://SERVICE-PRODUCER/user/{id}";
		return restTemplate.getForObject(url, Object.class, id);
	}

	@ResponseBody
	@GetMapping("{id}/feign")
	public Object userInfoFeign(@PathVariable Integer id) {
		return userService.getUser(id);
	}

}

