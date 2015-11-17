package com.xstar.config;

import java.io.File;



import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.jfinal.BeetlRenderFactory;



import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.demo.WeixinApiController;
import com.jfinal.weixin.demo.WeixinMsgController;
import com.jfinal.weixin.demo.WeixinPayController;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.xstar.shiro.ShiroTag;

public class XOneConfig extends JFinalConfig {

	/**
	 * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
	 * @param pro 生产环境配置文件
	 * @param dev 开发环境配置文件
	 */
	public void loadProp(String pro, String dev) {
		try {
			PropKit.use(pro);
		}
		catch (Exception e) {
			PropKit.use(dev);
		}
	}
	
	public void configConstant(Constants me) {
		loadProp("config_pro.txt", "config_dev.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));

		me.setMainRenderFactory(new BeetlRenderFactory());
		GroupTemplate groupTemplate = BeetlRenderFactory.groupTemplate;
		groupTemplate.registerFunctionPackage("so", new ShiroTag());
		groupTemplate.registerFunctionPackage("strkit", CoperaStrKit.class);
		groupTemplate.registerTag("dict", DictTag.class);
		WebAppResourceLoader loader = (WebAppResourceLoader)groupTemplate.getResourceLoader();
		loader.setRoot(PathKit.getWebRootPath()+File.separator+"WEB-INF"+File.separator+"pages"+File.separator);
		
		me.setError401View("/common/401.html");//没登录
		me.setError403View("/common/403.html");//没权限
		me.setError404View("/common/404.html");//没页面
		me.setError500View("/common/500.html");//服务器端异常

		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
	}
	
	public void configRoute(Routes me) {
		me.add("/msg", WeixinMsgController.class);
		me.add("/api", WeixinApiController.class, "/api");
		me.add("/pay", WeixinPayController.class);
	}
	
	public void configPlugin(Plugins me) {
		// C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		// me.add(c3p0Plugin);
		
		// EhCachePlugin ecp = new EhCachePlugin();
		// me.add(ecp);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		
	}
	
	public static void main(String[] args) {
		JFinal.start("webapp", 80, "/", 5);
	}

}
