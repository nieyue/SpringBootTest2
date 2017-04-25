package com.nieyue.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nieyue.common.IPCountUtil;


@RestController
public class TestController {
	@Autowired
	StringRedisTemplate stringRediseTemplate;
	@Autowired HttpServletRequest request; 
	@RequestMapping("/test")
	public  String test(HttpServletRequest request){
		BoundValueOperations<String, String> pvbso = stringRediseTemplate.boundValueOps("pvdata");
		BoundValueOperations<String, String> uvbso = stringRediseTemplate.boundValueOps("uvdata");
		BoundValueOperations<String, String> ipbso = stringRediseTemplate.boundValueOps("ipdata");
		 BoundSetOperations<String, String> ipsbso = stringRediseTemplate.boundSetOps("ipsdata");
		Long pvl = pvbso.increment(1);
		Long uvl = uvbso.increment(1);
		Long ipl = ipbso.increment(1);
		ipsbso.add(IPCountUtil.getIpAddr(request));
		//bso.set(l.toString());
		//bso.set(String.valueOf(Integer.valueOf(bso.get())+1));
		//return bso.get();
		return "upv:"+pvl.toString()+"uv:"+uvl.toString()+"ip:"+ipl.toString() ;
	}
	@RequestMapping("/test2")
	public  String test2(HttpServletRequest request){
		BoundValueOperations<String, String> pvbso = stringRediseTemplate.boundValueOps("pvdata");
		BoundValueOperations<String, String> uvbso = stringRediseTemplate.boundValueOps("uvdata");
		BoundValueOperations<String, String> ipbso = stringRediseTemplate.boundValueOps("ipdata");
		BoundListOperations<String, String> listbso = stringRediseTemplate.boundListOps("listdata");
		Long pvl = pvbso.increment(0);
		Long uvl = uvbso.increment(0);
		Long ipl = ipbso.increment(0);
		 Long listl = listbso.leftPush("a");
		return "upv:"+pvl.toString()+"uv:"+uvl.toString()+"ip:"+ipl.toString() ;
	}
	@RequestMapping("/ips")
	public  String ips(HttpServletRequest request){
		BoundSetOperations<String, String> ipsbso = stringRediseTemplate.boundSetOps("ipsdata");
		ipsbso.members().size();
		//bso.set(l.toString());
		//bso.set(String.valueOf(Integer.valueOf(bso.get())+1));
		//return bso.get();
		return "ips:"+ipsbso.members().size();
	}
	@RequestMapping("/listdata")
	public  String listdata(HttpServletRequest request){
		BoundListOperations<String, String> listbso = stringRediseTemplate.boundListOps("listdata");
		return "listdata:"+listbso.size();
	}
	@RequestMapping("/index")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	@Value("${myyml.name}")
	String[] arrayProps; 
	@RequestMapping("/tt")
	public 	String[] tt(HttpSession session){
		
		return arrayProps;
	}
	@RequestMapping("/date")
	public Date date(@RequestParam("date")Date date){
		System.out.println(date.toLocaleString());
		return date;
	}
	@RequestMapping("/date1")
	public String date1(@RequestParam("date")Date date){
		return date.toString();
	}
	@RequestMapping("/info")
	public String getInfo(@RequestParam("info") String info){
		System.out.println(info);
		return info.toString();
	}
	@RequestMapping("/session")
	public String getInfo(HttpSession session){
		int num=1;
		Object sessionnum = session.getAttribute("num");
		if(sessionnum==null){
			session.setAttribute("num", num);
		}else{
			session.setAttribute("num", (int)sessionnum+num);
		}
		System.out.println(session.getAttribute("num"));
		return "SessionId="+session.getId();
	}
}
